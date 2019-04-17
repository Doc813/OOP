import dao.CsvDao;
import dao.CsvDaoParameters;
import dao.DbDao;
import dao.DbDaoParameters;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
       DbDaoParameters param = new DbDaoParameters("jdbc:mysql://localhost:3306/shopper","root","1234");
        DbDao dao = new DbDao();
        dao.create(param);
        Service service = new Service();
        service.create(dao);
        service.createShop("Shop2");
        CsvDaoParameters csvParam = new CsvDaoParameters("G:/Учеба/University/2год/OOP/CsvDb");
        CsvDao csvDao = new CsvDao();
        csvDao.create(csvParam);
        Service csvService = new Service();
        csvService.create(csvDao);
        csvService.createShop("Shop");
        csvService.addProduct("Apple");

    }
}
