import dao.DAO;
import dao.Pair;
import dao.Product_prop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SI {

    void create(DAO dao);

    void createShop(String nameShop) throws SQLException, NullPointerException, IOException;

    void addThingToShop(String shopNameForAdd, String productNameForAdd, String amountOfProduct, String costOfProduct) throws Exception;

    void addProduct(String productName) throws SQLException, IOException;

    Product_prop findCheapestProduct(String cheapestProduct) throws Exception;

    int findAmountOfProduct(String shopName, String productName, String money) throws Exception;

    float calcProduct(String productName, String productAmount, String shopName) throws Exception;

    Pair<String, Float> findShop(List<Pair<String, String>> list) throws Exception;
}
