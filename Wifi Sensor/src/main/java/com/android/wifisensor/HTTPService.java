package com.android.wifisensor;


import android.util.Log;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * Created by aa on 14-2-11.
 */
public class HTTPService {

    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static final String URL_PRE = "http://lccpu3.cse.ust.hk/gmission";
    public static final String URL_BASE = URL_PRE + "/auto-api";

    public static final String URL_CATEGORY = URL_BASE + "/category";
    public static final String URL_LOCATION = URL_BASE + "/location";

    public static final String PARSE_APP_ID = "zHb2bVia6kgilYRWWdmTiEJooYA17NnkBSUVsr4H";
    public static final String PARSE_REST_API_KEY = "N2kCY1T3t3Jfhf9zpJ5MCURn3b25UpACILhnf5u9";
    public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
    public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * Read and connect timeout in milliseconds
     */
    private static final int TIMEOUT = 30 * 1000;


    private <T> ObjectParser<T> fromJsonRequest(HttpRequest request, Type type) throws JsonException {
        Type fooType = new TypeToken<ObjectParser<T>>() {}.getType();
        Reader reader = request.bufferedReader();
        try {
            /*
            try {
                Log.i("","" + reader.read());
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return GSON.fromJson(reader, type);

        } catch (JsonParseException e) {
            throw new JsonException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException ignored) {
                // Ignored
            }
        }
    }

    private String getQueryString(String name, String op, String val ) {
        String query = "?q={%22filters%22:[{%22name%22:%22" + name + "%22,%20%22op%22:%22" + op + "%22,%20%22val%22:%22" + val + "%22}]}";
        return query;
    }

    protected HttpRequest execute(final HttpRequest request) throws IOException {
        if (!configure(request).ok())
            throw new IOException("Unexpected response code: " + request.code());
        return request;
    }


    private HttpRequest configure(final HttpRequest request) {
        request.connectTimeout(TIMEOUT).readTimeout(TIMEOUT);
        //request.userAgent(userAgentProvider.get());

        if (isPostOrPut(request)) {
            // All PUT & POST requests to Parse.com api must be in JSON
            // https://www.parse.com/docs/rest#general-requests
            request.contentType(Constants.Http.CONTENT_TYPE_JSON);
        }

        return addCredentialsTo(request);
    }

    public List<Location> getLocation(LocationCategory locationCategory) throws IOException {
        try {
            final String query = getQueryString("category_id", "eq", String.valueOf(locationCategory.getId()));
            HttpRequest request = execute(HttpRequest.get(URL_LOCATION + query));

            Type fooType = new TypeToken<ObjectParser<Location>>() {}.getType();
            ObjectParser<Location> response = fromJsonRequest(request, fooType);


            if (response != null && response.objects != null) {
                return response.objects;
            }
            return Collections.emptyList();
        } catch (final HttpRequestException e) {
            throw e.getCause();
        }
    }

    public List<LocationCategory> getLocationCategory() throws IOException {

        try {

            HttpRequest request = execute(HttpRequest.get(URL_CATEGORY));

//            Log.i("", request.body().toString());

            Type fooType = new TypeToken<ObjectParser<LocationCategory>>() {}.getType();

            ObjectParser<LocationCategory> response = fromJsonRequest(request, fooType);

            Log.i("resuts: ", String.valueOf(response.num_results));
            Log.i("resuts: ", String.valueOf(response.objects.size()));
            if (response != null && response.objects != null) {
                return response.objects;
            }

            return Collections.emptyList();
        } catch (final HttpRequestException e) {
            throw e.getCause();
        }
    }



    private boolean isPostOrPut(final HttpRequest request) {
        return request.getConnection().getRequestMethod().equals(HttpRequest.METHOD_POST)
                || request.getConnection().getRequestMethod().equals(HttpRequest.METHOD_PUT);

    }

    private HttpRequest addCredentialsTo(final HttpRequest request) {

        // Required params for
        request.header(HEADER_PARSE_REST_API_KEY, PARSE_REST_API_KEY);
        request.header(HEADER_PARSE_APP_ID, PARSE_APP_ID);

        /*
         * NOTE: This may be where you want to add a header for the api token that was saved when
         * you logged in. In the bootstrap sample this is where we are saving the session id as
         * the token. If you actually had received a token you'd take the "apiKey" (aka: token)
         * and add it to the header or form values before you make your requests.
         *
         * Add the user name and password to the request here if your service needs username or
         * password for each request. You can do this like this:
         * request.basic("myusername", "mypassword");
         */

        return request;
    }



    private static class JsonException extends IOException {

        private static final long serialVersionUID = 3774706606129390273L;

        /**
         * Create exception from {@link JsonParseException}
         *
         * @param cause
         */
        public JsonException(final JsonParseException cause) {
            super(cause.getMessage());
            initCause(cause);
        }
    }

}
