package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuqisu on 4/28/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context,Schema.DATABASE_NAME,null,Schema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Schema.User.USERS_NAME
                + "(_id integer primary key autoincrement, "
                +Schema.User.Column.EMAIL + ", "
                +Schema.User.Column.PASSWORD + ", "
                +Schema.User.Column.FULL_NAME + ", "
                +Schema.User.Column.BIRTHDAY + ", "
                +Schema.User.Column.PICTURE_PATH + ", "
                +Schema.User.Column.HOME_TOWN + ", "
                +Schema.User.Column.Allergy + ", "
                +Schema.User.Column.WORKDay + ", "
                +Schema.User.Column.RESTDAY + ", "
                +Schema.User.Column.ExpanseLunch + ", "
                +Schema.User.Column.ExpanseDinner + ", "
                +Schema.User.Column.BIO + ")"
        );

        db.execSQL("CREATE TABLE " + Schema.foodPreference.Food
                + "(_id integer primary key autoincrement, "
                +Schema.foodPreference.Column.EMAIL + ", "
                +Schema.foodPreference.Column.TYPE + ", "
                +Schema.foodPreference.Column.NAME + ", "
                +Schema.User.Column.PICTURE_PATH + ", "
                +Schema.foodPreference.Column.RATING + ")"
        );

        db.execSQL("CREATE TABLE " + Schema.Favorite.FAVORITE
                + "(_id integer primary key autoincrement, "
                +Schema.Favorite.Column.EMAIL + ", "
                +Schema.Favorite.Column.FAVORITE + ")"
        );

        db.execSQL("CREATE TABLE " + Schema.Recommendation.RECOMMENDATION
                + "(_id integer primary key autoincrement, "
                +Schema.Recommendation.Column.CAL + ", "
                +Schema.Recommendation.Column.FIBER + ", "
                +Schema.Recommendation.Column.SODIUM + ", "
                +Schema.Recommendation.Column.NAME + ", "
                +Schema.User.Column.PICTURE_PATH + ", "
                +Schema.Recommendation.Column.TRANSPORTATION + ", "
                +Schema.Recommendation.Column.EstimateTime + ", "
                +Schema.Recommendation.Column.LOCATION + ", "
                +Schema.Recommendation.Column.PRICE + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
