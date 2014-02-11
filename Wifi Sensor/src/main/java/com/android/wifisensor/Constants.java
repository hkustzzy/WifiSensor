

package com.android.wifisensor;

/**
 * Bootstrap constants
 */
public final class Constants {
    private Constants() {}

    public static final class Auth {
        private Auth() {}

        /**
         * Account type id
         */
        public static final String BOOTSTRAP_ACCOUNT_TYPE = "com.hkust.android.gmission";

        /**
         * Account name
         */
        public static final String BOOTSTRAP_ACCOUNT_NAME = "GMission";

        /**
         * Provider id
         */
        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "com.hkust.android.gmission.sync";

        /**
         * Auth token type
         */
        public static final String AUTHTOKEN_TYPE = BOOTSTRAP_ACCOUNT_TYPE;
    }

    /**
     * All HTTP is done through a REST style API built for demonstration purposes on Parse.com
     * Thanks to the nice people at Parse for creating such a nice system for us to use for bootstrap!
     */
    public static final class Http {
        private Http() {}


        /**
         * Base URL for all requests
         */
//        public static final String URL_BASE = "https://api.parse.com";
//        public static final String URL_PRE = "http://192.168.56.1:5000";
//        public static final String URL_BASE = "http://192.168.56.1:5000/auto-api";
        public static final String URL_PRE = "http://lccpu3.cse.ust.hk/gmission";
        public static final String URL_BASE = URL_PRE + "/auto-api";
        /**
         * Authentication URL
         */
//        public static final String URL_AUTH = URL_BASE + "/1/login";

        /**
         * List Users URL
         */
        public static final String URL_USERS = URL_BASE + "/users";

        /**
         * List News URL
         */
        public static final String URL_NEWS = URL_BASE + "/1/classes/News";

        /**
         * List Checkin's URL
         */
        public static final String URL_CHECKINS = URL_BASE + "/1/classes/Locations";

        public static final String URL_CATEGORY = URL_BASE + "/category";
        public static final String URL_LOCATION = URL_BASE + "/location";
        public static final String URL_TASK = URL_BASE + "/task";
        public static final String URL_ANSWER = URL_BASE + "/answer";
        public static final String URL_AUTH = URL_PRE + "/user/login";
        public static final String URL_REG = URL_PRE + "/user/register";

        public static final String PARSE_APP_ID = "zHb2bVia6kgilYRWWdmTiEJooYA17NnkBSUVsr4H";
        public static final String PARSE_REST_API_KEY = "N2kCY1T3t3Jfhf9zpJ5MCURn3b25UpACILhnf5u9";
        public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SESSION_TOKEN = "sessionToken";


    }


    public static final class Extra {
        private Extra() {}

        public static final String NEWS_ITEM = "news_item";

        public static final String USER = "user";

        public static final String TAG_FRAGMENT = "TAG_FRAGMENT";

        public static final int    SCREEN_ASK = 0;

        public static final int    SCREEN_ANSWER = 1;

        public static final String TASK_ITEM = "task_item";

    }

    public static final class Intent {
        private Intent() {}

        /**
         * Action prefix for all intents created
         */
        public static final String INTENT_PREFIX = "com.hkust.android.gmission.";

    }

    public static class Notification {
        private Notification() {
        }

        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)
    }

}


