package com.itgenius.mvclibrary.manager.bus.event;

public class BusEventTemplate {

    private int param1;

    public BusEventTemplate(int param1) {
        this.param1 = param1;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }
}
