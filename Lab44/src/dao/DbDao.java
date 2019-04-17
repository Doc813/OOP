package dao;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DbDao implements DAO {
    private static Connection connection;
    private static PreparedStatement getShopID;
    private static PreparedStatement getProductID;
    private static PreparedStatement getShopName;
    private static PreparedStatement createShop;
    private static PreparedStatement addProduct;
    private static PreparedStatement addProductToShop;
    private static PreparedStatement findCheapestProduct;
    private static PreparedStatement findPriceInShop;
    private static PreparedStatement getPriceOfProduct;
    private static PreparedStatement getShopsID;

    @Override
    public void create(DaoParameters parameters) throws IOException, SQLException {
        if (parameters instanceof DbDaoParameters) {
            connection = DriverManager.getConnection(parameters.getUrl(), parameters.getUser(), parameters.getPasswd());
        }
        createShop = connection.prepareStatement("INSERT INTO shops (s_name) VALUES (?)");
        getShopID = connection.prepareStatement("SELECT s_ID FROM shops WHERE s_name = ?");
        getProductID = connection.prepareStatement("SELECT p_ID FROM products WHERE p_name = ?");
        getShopName = connection.prepareStatement("SELECT s_name FROM shops WHERE s_ID = ?");
        addProductToShop = connection.prepareStatement("INSERT INTO shops_products (sp_s_ID, sp_p_ID, sp_p_amount, sp_p_cost) VALUES (?, ?, ?, ?)");
        addProduct = connection.prepareStatement("INSERT INTO products (p_name) VALUES (?)");
        findCheapestProduct = connection.prepareStatement("SELECT sp_s_ID, sp_p_cost FROM shops_products WHERE sp_p_ID = ?");
        findPriceInShop = connection.prepareStatement("SELECT sp_p_cost FROM shops_products WHERE sp_s_ID = ? AND sp_p_ID = ?");
        getPriceOfProduct = connection.prepareStatement("SELECT sp_p_amount, sp_p_cost FROM shops_products WHERE sp_s_ID = ? AND sp_p_ID = ?");
        getShopsID = connection.prepareStatement("SELECT s_ID FROM shops");
    }

    @Override
    public void createShop(String s) throws SQLException {
        createShop.setString(1, s);
        createShop.executeUpdate();
    }

    @Override
    public void addThingToShop(String shopNameForAdd, String productNameForAdd, String amountOfProduct, String costOfProduct) throws Exception {
        getShopID.setString(1, shopNameForAdd);
        ResultSet rs = getShopID.executeQuery();
        if (!rs.next()) throw new Exception("there is no shop with name: " + shopNameForAdd);
        Integer shopId = rs.getInt(1);
        getProductID.setString(1, productNameForAdd);
        rs = getProductID.executeQuery();
        if(!rs.next()) throw new Exception("there is no product with name: " + productNameForAdd);;
        Integer productID = rs.getInt(1);
        if (shopId != null && productID != null){
            addProductToShop.setInt(1, shopId);
            addProductToShop.setInt(2, productID);
            addProductToShop.setString(3, amountOfProduct);
            addProductToShop.setString(4, costOfProduct);
            addProductToShop.executeUpdate();
        }
    }

    @Override
    public void addProduct(String productName) throws SQLException {
        addProduct.setString(1, productName);
        addProduct.executeUpdate();
    }

    @Override
    public Product_prop findCheapestProduct(String cheapestProduct) throws Exception {
        getProductID.setString(1,cheapestProduct);
        ResultSet rs = getProductID.executeQuery();
        if (!rs.next()) throw new Exception("there is no product with name: " + cheapestProduct);
        Integer productID = rs.getInt(1);
        findCheapestProduct.setInt(1, productID);
        rs = findCheapestProduct.executeQuery();

        if (!rs.next()) throw new Exception("there is no " + cheapestProduct + " int he shops");
        float min = rs.getFloat(2);
        int shop_ID = rs.getInt(1);
        while (rs.next()){
            float cur = rs.getFloat(2);
            if (cur < min) {
                min = cur;
                shop_ID = rs.getInt(1);
            }
            rs.next();
        }
        getShopName.setInt(1, shop_ID);
        rs = getShopName.executeQuery();
        rs.next();
        String shop_name = rs.getNString(1);
        return new Product_prop(shop_name, cheapestProduct, min);
    }

    @Override
    public int findAmountOfProduct(String shopName, String productName, String money) throws Exception {
        getShopID.setString(1, shopName);
        ResultSet rs = getShopID.executeQuery();
        if (!rs.next()) throw new Exception("there is no shop with name: " + shopName);
        int shopID = rs.getInt(1);
        getProductID.setString(1, productName);
        rs = getProductID.executeQuery();
        if (!rs.next()) throw new Exception("there is no product with name: " + productName);
        int productID = rs.getInt(1);
        findPriceInShop.setInt(1, shopID);
        findPriceInShop.setInt(2, productID);
        rs = findPriceInShop.executeQuery();
        if (!rs.next()) throw new Exception("there is no " + productName + " in " + shopName);
        float price = rs.getInt(1);
        return (int) (Integer.valueOf(money)/price);
    }

    @Override
    public float calcProduct(String shopName, String productName, String productAmount) throws Exception {
        getShopID.setString(1, shopName);
        ResultSet rs = getShopID.executeQuery();
        if (!rs.next()) throw new Exception("there is no shop with name: " + shopName);
        int shopID = rs.getInt(1);
        getProductID.setString(1, productName);
        rs = getProductID.executeQuery();
        if (!rs.next()) throw new Exception("there is no product with name: " + productName);
        int productID = rs.getInt(1);
        getPriceOfProduct.setInt(1, shopID);
        getPriceOfProduct.setInt(2, productID);
        rs = getPriceOfProduct.executeQuery();
        if (!rs.next()) throw new Exception("there is no " + productName + " in " + shopName);
        int amount = rs.getInt(1);
        if (amount < Integer.valueOf(productAmount)) throw new Exception("there is no " + productAmount + " " + productName);
        else {
            float price = rs.getFloat(2);
            return price * Integer.valueOf(productAmount);
        }
    }

    @Override
    public Pair<String, Float> findShop(List<Pair<String, String>> list) throws Exception {
        ResultSet rs = getShopsID.executeQuery();
        List<Pair<Integer,Float>> prices = new LinkedList<>();
        while(rs.next()) {
            int shopID = rs.getInt(1);
            float res = 0;
            boolean flag = false;
            for (Pair<String, String> p : list) {
                String productName = p.getKey();
                String productAmount = p.getValue();
                getProductID.setString(1, productName);
                ResultSet set = getProductID.executeQuery();
                if(!set.next()) throw new Exception("there is no " + productName);
                int productID = set.getInt(1);
                getPriceOfProduct.setInt(1, shopID);
                getPriceOfProduct.setInt(2, productID);
                set = getPriceOfProduct.executeQuery();
                if(!set.next()){
                    flag = true;
                    break;
                }
                int amount = set.getInt(1);
                if (amount < Integer.valueOf(productAmount)) {
                    flag = true;
                    break;
                }
                res += set.getFloat(2) * Integer.valueOf(productAmount);
            }
            if (flag == false) {
                prices.add(new Pair<>(shopID, res));
            }
        }
        Pair<Integer, Float> min = prices.get(0);
        for (Pair<Integer, Float> p : prices) {
            if(p.getValue() < min.getValue()) min = p;
        }
        if (min.getValue() == null) throw new Exception("there is no kit with yours parameters");
        getShopName.setInt(1, min.getKey());
        rs = getShopName.executeQuery();
        rs.next();
        String name = rs.getNString(1);
        return new Pair<>(name, min.getValue());
    }

    @Override
    public void close() {

    }
}
