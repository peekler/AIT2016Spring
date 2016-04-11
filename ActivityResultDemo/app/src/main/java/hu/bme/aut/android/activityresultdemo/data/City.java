package hu.bme.aut.android.activityresultdemo.data;

import java.io.Serializable;

/**
 * Created by peter on 2015. 11. 05..
 */
public class City implements Serializable {
    private String name;
    private String desc;

    public City(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
