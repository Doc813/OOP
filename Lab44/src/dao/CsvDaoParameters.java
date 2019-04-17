package dao;

public class CsvDaoParameters implements DaoParameters {
    final String path;

    public CsvDaoParameters(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPasswd() {
        return null;
    }

}
