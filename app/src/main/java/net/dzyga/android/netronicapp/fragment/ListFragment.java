package net.dzyga.android.netronicapp.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.activity.MainActivity;
import net.dzyga.android.netronicapp.adapter.UsersAdapter;
import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.net.NetworkClient;
import net.dzyga.android.netronicapp.net.responses.UsersResponse;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "ListFragment";
    private UsersAdapter adapter;
    private List<User> data = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ListView lv = rootView.findViewById(R.id.list);
        adapter = new UsersAdapter(getActivity(), data);
        lv.setAdapter(adapter);
        lv.setEmptyView(rootView.findViewById(R.id.empty));
        lv.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callUsers();
    }

    private void callUsers() {
        Call<UsersResponse> call = NetworkClient.getInstance().getRestApi()
                .getUsers(20);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, ""+response.body().results.get(0).picture.medium);
                    data.clear();
                    data.addAll(response.body().results);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.e(TAG, "getUsers failed");
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserFragment fr = new UserFragment();
        fr.setUser(data.get(position));
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("list")
                .replace(R.id.main_fragment_holder, fr).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbar(true);
    }
}
