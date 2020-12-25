package com.app.ddd.ApiCalling;

import com.app.ddd.Response.CourseUnitResponse;
import com.app.ddd.Response.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiInterface {

    public static String HOST = "https://ddd.flynautstudio.com/api/"; // production
//    public static String HOST = "https://ddd.flynautstudio.com/api/"; // Live

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("auth/forgotPassword")
    Call<Object> forgotPassword(@Body JsonObject object);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("auth/login")
    Call<LoginResponse> login(@Body JsonObject object);

    //user

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("users/getCourseUnits")
    Call<CourseUnitResponse> getCourseUnits(@Body JsonObject object);

    /*******************************************************************************/

    class Creator {
        public static Retrofit retrofit = null;

        public static ApiInterface newFullTeamService() {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //interceptor.setLogLevel(RestAdapter.LogLevel.FULL)
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(HOST)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            return retrofit.create(ApiInterface.class);
        }
    }

    class ResponseApi<T> {

        private String status;
        private String message;
        private T data;

        public ResponseApi(String status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }
}