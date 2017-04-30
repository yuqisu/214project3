package model;

/**
 * Created by yuqisu on 4/28/17.
 */

public class User {
    private String fullname;
    private String password;
    private String birthday;
    private String hometown;
    private String profilePic;
    private double expanseLunch;
    private double expanseDinner;
    private String bio;
    private String email;
    private String workday;
    private String restday;
    private String allergy;


    public User(){

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getRestday() {
        return restday;
    }

    public String getWorkday() {
        return workday;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public void setRestday(String restday) {
        this.restday = restday;
    }

    public void setWorkday(String workday) {
        this.workday = workday;
    }

    public void setExpanseDinner(double expanseDinner) {
        this.expanseDinner = expanseDinner;
    }

    public void setExpanseLunch(double expanseLunch) {
        this.expanseLunch = expanseLunch;
    }

    public double getExpanseDinner() {
        return expanseDinner;
    }

    public double getExpanseLunch() {
        return expanseLunch;
    }
}
