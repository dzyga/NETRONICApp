package net.dzyga.android.netronicapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.dzyga.android.netronicapp.model.DataBusState;
import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.repository.UsersRepository;

import java.util.List;

/**
 * Created by Mr.Dzyga on 13.09.2020.
 */
public class UsersViewModel extends AndroidViewModel {
    private LiveData<List<User>> usersLiveData;
    private LiveData<DataBusState> stateLiveData;

    public UsersViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        usersLiveData = UsersRepository.getInstance().getUsers();
        stateLiveData = UsersRepository.getInstance().getState();
    }

    public LiveData<DataBusState> getState(){
        return stateLiveData;
    }

    public LiveData<List<User>> getUsers() {
        return usersLiveData;
    }

    public LiveData<List<User>> refreshUsers() {
        usersLiveData = UsersRepository.getInstance().callUsers();
        return usersLiveData;
    }
}
