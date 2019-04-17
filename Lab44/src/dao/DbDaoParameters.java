package dao;

public class DbDaoParameters implements DaoParameters {
    private final String url;
    private final String user;
    private final String passwd;

    public DbDaoParameters(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }


    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getPath() {
        return null;
    }
}
