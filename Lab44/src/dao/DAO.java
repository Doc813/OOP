package dao;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO {

    void create(DaoParameters parameters) throws IOException, SQLException;

    void createShop(String shopName) throws SQLException, IOException;

    void addThingToShop(String shopNameForAdd, String productNameForAdd, String amountOfProduct, String costOfProduct) throws Exception;

    void close();

    void addProduct(String productName) throws SQLException, IOException;

    Product_prop findCheapestProduct(String cheapestProduct) throws Exception;

    int findAmountOfProduct(String shopName, String productName, String money) throws Exception;

    float calcProduct(String shopName, String productName, String productAmount) throws Exception;

    Pair<String, Float> findShop(List<Pair<String, String>> list) throws Exception;
}
