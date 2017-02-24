package com.taku.pbattle.mvvm.Model;

import java.io.Serializable;

/**
 * Created by TAKU on 2017/02/24.
 */

public class DrawerItem implements Serializable {

    private String action;

    public DrawerItem() {
    }

    public DrawerItem(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
