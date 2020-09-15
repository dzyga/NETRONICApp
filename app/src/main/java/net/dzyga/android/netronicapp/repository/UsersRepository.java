package net.dzyga.android.netronicapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.dzyga.android.netronicapp.model.DataBusState;
import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.net.NetworkClient;
import net.dzyga.android.netronicapp.net.responses.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Dzyga on 13.09.2020.
 */
public class UsersRepository {
    private final String TAG = getClass().getSimpleName();
    private static UsersRepository instance;
    private final MutableLiveData<List<User>> mutableLiveData;
    private final MutableLiveData<DataBusState> stateLiveData;

    private UsersRepository(){
        mutableLiveData = new MutableLiveData<>();
        stateLiveData = new MutableLiveData<>();
        stateLiveData.postValue(DataBusState.NOTHING);
    }

    public MutableLiveData<List<User>> getUsers(){
        if (mutableLiveData.getValue()==null || mutableLiveData.getValue().isEmpty()) requestUsers();
        return mutableLiveData;
    }

    public MutableLiveData<List<User>> callUsers(){
        requestUsers();
        return mutableLiveData;
    }

    public static UsersRepository getInstance(){
        if (instance==null) instance = new UsersRepository();
        return instance;
    }

    private MutableLiveData<List<User>> requestUsers() {
        stateLiveData.postValue(DataBusState.LOADING);
        Call<UsersResponse> call = NetworkClient.getInstance().getRestApi().getUsers(20);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    UsersResponse temp = response.body();
                    if (temp!=null){
                        mutableLiveData.postValue(temp.results);
                        stateLiveData.postValue(DataBusState.READY);
                    } else {
                        stateLiveData.postValue(DataBusState.ERROR);
                    }
                } else {
                    stateLiveData.postValue(DataBusState.ERROR);
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                stateLiveData.postValue(DataBusState.ERROR);
            }
        });
        return mutableLiveData;
    }

    public LiveData<DataBusState> getState() {
        return stateLiveData;
    }
}
