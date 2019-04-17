package dao;

public class Product_prop {
    private String shop_name;
    private String product_name;
    private float product_price;

    public Product_prop(String shop_name, String product_name, float product_price) {
        this.shop_name = shop_name;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public float getProduct_price() {
        return product_price;
    }
}

