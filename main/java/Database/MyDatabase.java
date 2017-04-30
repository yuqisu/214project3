package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.FoodPreference;
import model.Recommendation;
import model.User;

/**
 * Created by yuqisu on 4/28/17.
 */

public class MyDatabase {

    private static MyDatabase mdatabase;
    private User currentUser;
    private final SQLiteDatabase sqLiteDatabase;

    public MyDatabase(Context context) {
        sqLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
    }

    public static MyDatabase get(Context context){
        if (mdatabase==null){
            mdatabase = new MyDatabase(context);
        }
        return mdatabase;
    }

    public User getUser(String email, String password){
        Cursor cursor = sqLiteDatabase.query(Schema.User.USERS_NAME,null,
                "email = ? AND password = ?",new String[]{email,password},null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);
        User user;
        if (wrapper.getCount()>0){
            wrapper.moveToFirst();
            user = wrapper.getUser();
        }else{
            user=null;
        }
        wrapper.close();

        return user;
    }

    public List<User> getUserList(){
        List<User> userlist = new ArrayList<>();
        UserCursorWrapper wrapper = new UserCursorWrapper(sqLiteDatabase.query(
                Schema.User.USERS_NAME,null,null,null,null,null,null
        ));
        try{ wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                User user = wrapper.getUser();
                userlist.add(user);
                wrapper.moveToNext();
            }

        }finally {
            wrapper.close();
        }
        return userlist;
    }

    public List<FoodPreference> getFoodList(String email){
        List<FoodPreference> feedlist = new ArrayList<>();
        FoodCursorWrapper wrapper = new FoodCursorWrapper(sqLiteDatabase.query(
                Schema.foodPreference.Food,null,null,null,null,null,null
        ));
        try{ wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){

                FoodPreference preference = wrapper.getFeed();
//                System.out.println("gettttt "+feed.getEmail());
                if (email.equals(preference.getEmail()) || checkFavorite(email,preference.getEmail())){
                    feedlist.add(preference);
                    wrapper.moveToNext();
                }else
                    wrapper.moveToNext();
            }

        }finally {
            wrapper.close();
        }
        return feedlist;
    }




    public boolean checkEmail(String email){
        Cursor cursor = sqLiteDatabase.query(Schema.User.USERS_NAME,null,"email = ? ",new String[]{email},null,null,null);
        if (cursor.moveToFirst()){
            return true;
        }
        cursor.close();
        return false;
    }
    public void logDatabse(){
        Cursor cursor = sqLiteDatabase.query(
                Schema.User.USERS_NAME,null,null,null,null,null,null
        );
//        Log.d("user",""+cursor.getString(cursor.getColumnIndex("email")));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void insertUser(User user){
        ContentValues values = getUserContentValues(user);
        logDatabse();
        sqLiteDatabase.insert(Schema.User.USERS_NAME,null,values);
    }
    private static ContentValues getUserContentValues(User user){
        ContentValues values = new ContentValues();

        values.put(Schema.User.Column.EMAIL,user.getEmail());
        values.put(Schema.User.Column.FULL_NAME,user.getFullname());
        values.put(Schema.User.Column.HOME_TOWN,user.getHometown());
        values.put(Schema.User.Column.PASSWORD,user.getPassword());
        try{
            values.put(Schema.User.Column.BIRTHDAY,user.getBirthday());
        }catch (NullPointerException e){

        }
        values.put(Schema.User.Column.PICTURE_PATH,user.getProfilePic());
        values.put(Schema.User.Column.BIO,user.getBio());
        values.put(Schema.User.Column.WORKDay,user.getWorkday());
        values.put(Schema.User.Column.RESTDAY,user.getRestday());
        values.put(Schema.User.Column.Allergy,user.getAllergy());
        values.put(Schema.User.Column.Allergy,user.getAllergy());
        values.put(Schema.User.Column.ExpanseLunch,user.getExpanseDinner());
        values.put(Schema.User.Column.ExpanseDinner,user.getExpanseDinner());

        return values;

    }


    public boolean checkFavorite(String email,String Id){
            Cursor cursor= sqLiteDatabase.query(Schema.Favorite.FAVORITE,null,"id = ? AND email = ? AND favorite = ?",new String[]{Id,email,"true"},null,null,null);
        if (cursor.moveToFirst()){
            return true;
        }
        cursor.close();
        return false;
    }

    public void updateFavorite(String name,String id,String favorite){

        ContentValues values= new ContentValues();
        values.put(Schema.Favorite.Column.EMAIL,name);
        values.put(Schema.Favorite.Column.FAVORITE,favorite);
        sqLiteDatabase.update(Schema.Favorite.FAVORITE,values,Schema.Favorite.Column.FAVORITE+"=?",new String[]{"true"});

    }


    public void insertFood(FoodPreference food){
        ContentValues values = getFoodContentValues(food);
        sqLiteDatabase.insert(Schema.foodPreference.Food,null,values);
    }
    private static ContentValues getFoodContentValues(FoodPreference food){
        ContentValues values = new ContentValues();

        values.put(Schema.foodPreference.Column.EMAIL,food.getEmail());
        values.put(Schema.foodPreference.Column.NAME,food.getName());
        values.put(Schema.foodPreference.Column.TYPE,food.getType());
        values.put(Schema.foodPreference.Column.PICTURE_PATH,food.getFoodPicture());
        values.put(Schema.foodPreference.Column.RATING,food.getRating());


        return values;

    }

    public void insertFavorite(String name,String id,String favorite){
        ContentValues values = new ContentValues();
        values.put(Schema.Favorite.Column.EMAIL,name);
        values.put(Schema.Favorite.Column.FAVORITE,favorite);

        sqLiteDatabase.insert(Schema.Favorite.FAVORITE,null,values);
    }

    public void insertRecommondation(Recommendation rec){
        ContentValues values = getRecContentValues(rec);
        logDatabse();
        sqLiteDatabase.insert(Schema.User.USERS_NAME,null,values);
    }
    private static ContentValues getRecContentValues(Recommendation rec){
        ContentValues values = new ContentValues();

        values.put(Schema.Recommendation.Column.NAME,rec.getName());
        values.put(Schema.Recommendation.Column.LOCATION, String.valueOf(rec.getLocation()));
        values.put(Schema.Recommendation.Column.CAL,rec.getCal());
        values.put(Schema.Recommendation.Column.FIBER,rec.getFiber());
        values.put(Schema.Recommendation.Column.PICTURE_PATH,rec.getFoodPicture());
        values.put(Schema.Recommendation.Column.EstimateTime,rec.getTime());
        values.put(Schema.Recommendation.Column.PRICE,rec.getPrice());
        values.put(Schema.Recommendation.Column.SODIUM,rec.getSodium());
        values.put(Schema.Recommendation.Column.TRANSPORTATION,rec.getTransportation());

        return values;

    }

    public static class UserCursorWrapper extends CursorWrapper{

        /**
         * Creates a cursor wrapper.
         *
         * @param cursor The underlying cursor to wrap.
         */
        public UserCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public User getUser(){
            User user = new User();

            user.setEmail(getString(getColumnIndex(Schema.User.Column.EMAIL)));
            user.setFullname( getString(getColumnIndex(Schema.User.Column.FULL_NAME)));
            user.setPassword(getString(getColumnIndex(Schema.User.Column.PASSWORD)));
            user.setHometown(getString(getColumnIndex(Schema.User.Column.HOME_TOWN)));
            user.setBio(getString(getColumnIndex(Schema.User.Column.BIO)));
            user.setProfilePic(getString(getColumnIndex(Schema.User.Column.PICTURE_PATH)));
            user.setBirthday(getString(getColumnIndex(Schema.User.Column.BIRTHDAY)));
            user.setWorkday(getString(getColumnIndex(Schema.User.Column.WORKDay)));
            user.setRestday(getString(getColumnIndex(Schema.User.Column.RESTDAY)));
            user.setAllergy(getString(getColumnIndex(Schema.User.Column.Allergy)));

            return user;
        }



    }

    public static class FoodCursorWrapper extends CursorWrapper{

        /**
         * Creates a cursor wrapper.
         *
         * @param cursor The underlying cursor to wrap.
         */
        public FoodCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public FoodPreference getFeed(){
            FoodPreference food = new FoodPreference();
            food.setEmail(getString(getColumnIndex(Schema.foodPreference.Column.EMAIL)));
            food.setFoodPicture(getString(getColumnIndex(Schema.foodPreference.Column.PICTURE_PATH)));
            food.setName(getString(getColumnIndex(Schema.foodPreference.Column.NAME)));
            food.setRating(getDouble(getColumnIndex(Schema.foodPreference.Column.RATING)));

            return food;
        }



    }
}
