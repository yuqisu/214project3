package model;

/**
 * Created by yuqisu on 4/28/17.
 */

public class FoodPreference {
    private String email;
    private String type;
    private String name;
    private String foodPicture;
    private Double rating;

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getRating() {
        return rating;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }
}
