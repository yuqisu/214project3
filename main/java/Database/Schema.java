package Database;

/**
 * Created by yuqisu on 4/13/17.
 */

public class Schema {
    public static final String DATABASE_NAME = "food.db";
    public static final int VERSION = 1;

    public static class User{
        public static final String USERS_NAME = "users";

        public static class Column{
            public static final String EMAIL = "email";
            public static final String FULL_NAME ="fullName";
            public static final String BIRTHDAY ="birthday";
            public static final String HOME_TOWN ="hometown";
            public static final String PASSWORD = "password";
            public static final String PICTURE_PATH = "picturePath";
            public static final String BIO = "bio";
            public static final String WORKDay ="work";
            public static final String RESTDAY = "rest";
            public static final String Allergy = "allergy";
            public static final String ExpanseLunch = "expanseL";
            public static final String ExpanseDinner = "expanseD";
        }




    }

    public static class foodPreference{
        public static final String Food = "foods";

        public static class Column{
            public static final String EMAIL = "email";
            public static final String TYPE = "type";
            public static final String NAME = "name";
            public static final String RATING = "rating";
            public static final String PICTURE_PATH = "picturePath";
        }
    }

    public static class Favorite{
        public static final String FAVORITE = "favorite";
        public static class Column{
            public static final String EMAIL = "email";
            public static final String FAVORITE = "favorite";
        }
    }

    public static class Recommendation{
        public static final String RECOMMENDATION = "recommendation";
        public static class Column{
            public static final String NAME = "name";
            public static final String PICTURE_PATH = "picturePath";
            public static final String CAL = "cal";
            public static final String FIBER = "fiber";
            public static final String SODIUM = "sodium";
            public static final String TRANSPORTATION = "transportation";
            public static final String EstimateTime = "time";
            public static final String LOCATION = "location";
            public static final String PRICE = "price";
        }
    }


}
