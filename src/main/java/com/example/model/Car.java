package com.example.model;

public class Car {
    private int ID;
    private String modelName;
    private String brandName;
    private int year;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + ID +
                ", modelName='" + modelName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public static Car create(String modelName, String brandName, int year) {
        Car car = new Car();
        car.setBrandName(brandName);
        car.setModelName(modelName);
        car.setYear(year);
        return car;
    }
}
