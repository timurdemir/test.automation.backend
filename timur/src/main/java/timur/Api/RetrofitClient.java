package timur.Api;

import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;

public class RetrofitClient {

    public static Retrofit retrofit;

    private RetrofitClient() {

    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.1/").client(getClient()).build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(100, TimeUnit.SECONDS);
        return client;
    }

}