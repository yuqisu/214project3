package model;



/**
 * Created by yuqisu on 4/28/17.
 */

public class Recommendation {
    private String name;
    private double cal;
    private double fiber;
    private double sodium;
    private String foodPicture;
    private String transportation;
    private double time;
    private double[] location;
    private double price;

    public Recommendation(){

    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    public String getTransportation() {
        return transportation;
    }

    public double getCal() {
        return cal;
    }

    public double getFiber() {
        return fiber;
    }

    public double getPrice() {
        return price;
    }

    public double getSodium() {
        return sodium;
    }

    public double getTime() {
        return time;
    }

    public double[] getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }
    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }
}

