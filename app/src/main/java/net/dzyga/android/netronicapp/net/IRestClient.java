package net.dzyga.android.netronicapp.net;

import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.net.responses.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IRestClient {

    @GET("/api/")
    Call<UsersResponse> getUsers(@Query("results") int results);
}

