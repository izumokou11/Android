package com.example.myapplication.models;

public class CategoryModel {
    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String name;
    String color;

    public CategoryModel(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
