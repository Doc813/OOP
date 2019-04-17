package dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CsvDao implements DAO {
    String path;
    int shopID;
    int productID;
    File s_f;
    File p_f;
    File p_p_f;

    @Override
    public void create(DaoParameters parameters) throws IOException {
        if(parameters instanceof CsvDaoParameters) {
            this.path = parameters.getPath();
        }
        s_f = new File(path, "shops.csv");
        p_f = new File(path, "products.csv");
        p_p_f = new File(path, "shops_products.csv");

        if (!s_f.exists()) s_f.createNewFile();
        if (!p_f.exists()) p_f.createNewFile();
        if (!p_p_f.exists()) p_p_f.createNewFile();


        CSVReader shopReader = new CSVReader(new FileReader(s_f), ',', '"');
        shopID = 1;
        try {
            List<String[]> rows = shopReader.readAll();
            String[] s = rows.get(rows.size() - 1);
            shopID = Integer.parseInt(s[0]) + 1;
        } catch(Exception ex){}
        CSVReader shopsProducts = new CSVReader(new FileReader(p_p_f), ',', '"');
        productID = 1;
        try {
            List<String[]> rows = shopsProducts.readAll();
            String[] s = rows.get(rows.size() - 1);
            productID = Integer.parseInt(s[0]) + 1;
        } catch(Exception ex){}
    }

    @Override
    public void createShop(String shopName) throws IOException {
        CSVWriter shopWriter = new CSVWriter(new FileWriter(s_f, true));
        String[] write = {String.valueOf(shopID),shopName};
        shopWriter.writeNext(write);
        shopID++;
        shopWriter.close();
    }

    @Override
    public void addThingToShop(String shopNameForAdd, String productNameForAdd, String amountOfProduct, String costOfProduct) throws Exception {
        CSVReader shopReader = new CSVReader(new FileReader(s_f), ',', '"');
        Integer sID = null;
        List<String[]> rows = shopReader.readAll();
        for (String[] r : rows) {
            if (r[1].equals(shopNameForAdd)) {
                sID = Integer.valueOf(r[0]);
                break;
            }
        }
        if (sID == null) throw new Exception("no shops with name: " + shopNameForAdd);
        shopReader.close();
        CSVReader productReader = new CSVReader(new FileReader(p_f), ',', '"');
        Integer pID = null;
        rows = productReader.readAll();
        for (String[] r : rows){
            if (r[1].equals(productNameForAdd)){
                pID = Integer.valueOf(r[0]);
                break;
            }
        }
        if (pID == null) throw new Exception("no product with name: " + productNameForAdd);
        productReader.close();
        CSVWriter addToShop = new CSVWriter(new FileWriter(p_p_f, true));
        String[] write = {String.valueOf(sID), String.valueOf(pID),amountOfProduct,costOfProduct};
        addToShop.writeNext(write);
        addToShop.close();
    }

    @Override
    public void addProduct(String productName) throws IOException {
        CSVWriter productWriter = new CSVWriter(new FileWriter(p_f, true));
        String[] write = {String.valueOf(productID), productName};
        productWriter.writeNext(write);
        productID++;
        productWriter.close();
    }

    @Override
    public Product_prop findCheapestProduct(String cheapestProduct) throws Exception {
        CSVReader productReader = new CSVReader(new FileReader(p_f), ',', '"');
        Integer pID = null;
        List<String[]> rows = productReader.readAll();
        for (String[] r : rows){
            if (r[1].equals(cheapestProduct)){
                pID = Integer.valueOf(r[0]);
                break;
            }
        }
        if (pID == null) throw new Exception("no product with name: " + cheapestProduct);
        productReader.close();
        CSVReader products = new CSVReader(new FileReader(p_p_f), ',','"');
        rows = products.readAll();
        products.close();
        float price = Float.valueOf(rows.get(0)[3]);
        int s_ID = Integer.valueOf(rows.get(0)[0]);
        for (String[] r : rows){
            float temp = Float.valueOf(r[3]);
            if(temp < price ){
                price = temp;
                s_ID = Integer.valueOf(r[0]);
            }
        }
        products.close();
        CSVReader shopReader = new CSVReader(new FileReader(s_f), ',', '"');
        String sName = null;
        rows = shopReader.readAll();
        for (String[] r : rows) {
            if (Integer.valueOf(r[0]) == s_ID) {
                sName = String.valueOf(r[1]);
                break;
            }
        }
        shopReader.close();
        return new Product_prop(sName, cheapestProduct, price);
    }

    @Override
    public int findAmountOfProduct(String shopName, String productName, String money) throws Exception {
        CSVReader shopReader = new CSVReader(new FileReader(s_f), ',', '"');
        Integer sID = null;
        List<String[]> rows = shopReader.readAll();
        for (String[] r : rows) {
            if (r[1].equals(shopName)) {
                sID = Integer.parseInt(r[0]);
                break;
            }
        }
        if (sID == null) throw new Exception("no shops with name: " + shopName);
        shopReader.close();
        CSVReader productReader = new CSVReader(new FileReader(p_f), ',', '"');
        Integer pID = null;
        rows = productReader.readAll();
        for (String[] r : rows){
            if (r[1].equals(productName)){
                pID = Integer.parseInt(r[0]);
                break;
            }
        }
        if (pID == null) throw new Exception("no product with name: " + productName);
        productReader.close();
        CSVReader products = new CSVReader(new FileReader(p_p_f), ',','"');
        rows = products.readAll();
        products.close();
        Float price = null;
        for (String[] r : rows){
            if (Integer.parseInt(r[0]) == sID && Integer.parseInt(r[1]) == pID){
                price = Float.parseFloat(r[3]);
                break;
            }
        }
        return (int) (Float.parseFloat(money)/price);
    }

    @Override
    public float calcProduct(String shopName, String productName, String productAmount) throws Exception {
        CSVReader shopReader = new CSVReader(new FileReader(s_f), ',', '"');
        Integer sID = null;
        List<String[]> rows = shopReader.readAll();
        for (String[] r : rows) {
            if (r[1].equals(shopName)) {
                sID = Integer.parseInt(r[0]);
                break;
            }
        }
        if (sID == null) throw new Exception("no shops with name: " + shopName);
        shopReader.close();
        CSVReader productReader = new CSVReader(new FileReader(p_f), ',', '"');
        Integer pID = null;
        rows = productReader.readAll();
        for (String[] r : rows){
            if (r[1].equals(productName)){
                pID = Integer.parseInt(r[0]);
                break;
            }
        }
        if (pID == null) throw new Exception("no product with name: " + productName);
        productReader.close();
        CSVReader products = new CSVReader(new FileReader(p_p_f), ',','"');
        rows = products.readAll();
        products.close();
        Float price = null;
        Integer amount = null;
        for (String[] r : rows){
            if (Integer.parseInt(r[0]) == sID && Integer.parseInt(r[1]) == pID){
                amount = Integer.parseInt(r[2]);
                price = Float.parseFloat(r[3]);
                break;
            }
        }
        if (amount < Integer.parseInt(productAmount)) new Exception("no enough product");
        return Integer.parseInt(productAmount) * price;
    }

    @Override
    public Pair<String, Float> findShop(List<Pair<String, String>> list) throws Exception {
        CSVReader shops = new CSVReader(new FileReader(s_f), ',','"');
        List<String[]> rows = shops.readAll();
        shops.close();
        List<Integer> shopsID = new LinkedList<>();
        for (String[] r : rows){
            System.out.println(r[0]);
            shopsID.add(Integer.parseInt(r[0]));
        }
        List<Pair<Integer,Float>> prices = new LinkedList<>();
        for(Integer ID : shopsID){
            float res = 0;
            boolean flag = false;
            for (Pair<String, String> p : list) {
                String productName = p.getKey();
                String productAmount = p.getValue();

                CSVReader productReader = new CSVReader(new FileReader(p_f), ',', '"');
                Integer pID = null;
                rows = productReader.readAll();
                for (String[] r : rows){
                    if (r[1].equals(productName)){
                        pID = Integer.valueOf(r[0]);
                        break;
                    }
                }
                if (pID == null) throw new Exception("no product with name: " + productName);
                productReader.close();

                CSVReader getPrice = new CSVReader(new FileReader(p_p_f), ',', '"');
                Float price = null;
                Integer amount = null;
                rows = getPrice.readAll();
                getPrice.close();
                for (String[] r : rows){
                    if (Integer.parseInt(r[1]) == (pID) && Integer.parseInt(r[0]) == ID){
                        price = Float.parseFloat(r[3]);
                        amount = Integer.parseInt(r[2]);
                        break;
                    }
                }
                if (price == null || amount == null){
                    flag = true;
                    break;
                }
                if (amount < Integer.valueOf(productAmount)) {
                    flag = true;
                    break;
                }
                res += price * Integer.valueOf(productAmount);
            }
            if (flag == false) {
                prices.add(new Pair<>(ID, res));
            }
        }
        Pair<Integer, Float> min = prices.get(0);
        for (Pair<Integer, Float> p : prices) {
            if(p.getValue() < min.getValue()) min = p;
        }
        if (min.getValue() == null) throw new Exception("there is no kit with yours parameters");

        CSVReader shopName = new CSVReader(new FileReader(s_f), ',','"');
        rows = shopName.readAll();
        shopName.close();
        String name = null;
        for (String[] r : rows){
            if (Integer.parseInt(r[0]) == (min.getKey())){
                name = r[1];
            }
        }
        return new Pair<>(name, min.getValue());
    }

    @Override
    public void close() {

    }
}
