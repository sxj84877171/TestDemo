package com.happy.travel.entity;

/**
 * Created by elvissun on 8/16/2016.
 */
public class Category {


    private String name;
    private int icon;
    private Class<?> className;

    public Category() {
    }

    public Category(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public Category(String name, int icon, Class<?> className) {
        this.name = name;
        this.icon = icon;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }
}
