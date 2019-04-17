import dao.DAO;
import dao.Pair;
import dao.Product_prop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Service implements SI{

    private DAO currentDao;

    @Override
    public void create(DAO dao) {
        currentDao = dao;
    }

    @Override
    public void createShop(String nameShop) throws SQLException, NullPointerException, IOException {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        currentDao.createShop(nameShop);
    }

    @Override
    public void addThingToShop(String shopNameForAdd, String productNameForAdd, String amountOfProduct, String costOfProduct) throws Exception {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        currentDao.addThingToShop(shopNameForAdd, productNameForAdd, amountOfProduct, costOfProduct);
    }

    @Override
    public void addProduct(String productName) throws SQLException, IOException {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        currentDao.addProduct(productName);
    }

    @Override
    public Product_prop findCheapestProduct(String cheapestProduct) throws Exception {
        if(currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        return currentDao.findCheapestProduct(cheapestProduct);
    }

    @Override
    public int findAmountOfProduct(String shopName, String productName, String money) throws Exception {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        return currentDao.findAmountOfProduct(shopName, productName, money);
    }

    @Override
    public float calcProduct(String productName, String productAmount, String shopName) throws Exception {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        return currentDao.calcProduct(shopName, productName, productAmount);
    }

    @Override
    public Pair<String, Float> findShop(List<Pair<String, String>> list) throws Exception {
        if (currentDao == null){
            throw new NullPointerException("no connection. Connect to the db and try again");
        }
        return currentDao.findShop(list);
    }
}
