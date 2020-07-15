package net.dzyga.android.netronicapp.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.dzyga.android.netronicapp.utils.ConnectivityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static final String TAG = "NetworkClient";
    private static final int CONNECTION_TIMEOUT = 30000;
    private IRestClient restApi;
    private static NetworkClient instance;
    private Response resp;

    private NetworkClient() {
        Retrofit retrofit = initRetrofitLibrary();
        restApi = retrofit.create(IRestClient.class);
    }

    private Retrofit initRetrofitLibrary() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor inner= new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                if(!ConnectivityUtils.isConnected()){
                    throw  new NoInternetException("No internet connection");
                }

                Request request = chain.request();
                Response response = chain.proceed(request);
                setResp(response);

                if(response.code() == 401){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    request = chain.request();
                    response = chain.proceed(request);
                }
                return response;
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(inner)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://randomuser.me")
                .build();
    }

    public static NetworkClient getInstance() {
        if(instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public IRestClient getRestApi() {
        return restApi;
    }

    public synchronized Response getResp() {
        return resp;
    }

    public synchronized void setResp(Response resp) {
        this.resp = resp;
    }

    private void refreshToken(String refreshToken) {
        
    }
}
