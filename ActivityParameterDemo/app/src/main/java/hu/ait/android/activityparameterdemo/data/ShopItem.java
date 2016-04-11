package hu.ait.android.activityparameterdemo.data;

import java.io.Serializable;

public class ShopItem implements Serializable {
    String name;
    String price;

    public ShopItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
