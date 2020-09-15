package net.dzyga.android.netronicapp.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.adapter.UsersAdapter;
import net.dzyga.android.netronicapp.model.DataBusState;
import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {
    private UsersAdapter rAdapter;
    private List<User> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private UsersViewModel usersViewModel;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.list);
        rAdapter = new UsersAdapter(getActivity(), data);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(llm);
        rView.setAdapter(rAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rView.getContext(), llm.getOrientation());
        rView.addItemDecoration(dividerItemDecoration);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_feed);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            usersViewModel.refreshUsers().observe(getViewLifecycleOwner(), users -> refreshList(users));
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        usersViewModel.getUsers().observe(getViewLifecycleOwner(), users -> refreshList(users));
        usersViewModel.getState().observe(getViewLifecycleOwner(), dataBusState -> handleState(dataBusState));
    }

    private void handleState(DataBusState dataBusState) {
        switch (dataBusState){
            case LOADING:
                swipeRefreshLayout.setRefreshing(true);
                break;
            case ERROR:
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), R.string.loading_error, Toast.LENGTH_LONG).show();
                break;
            default:
                swipeRefreshLayout.setRefreshing(false);
                break;
        }
    }

    private void refreshList(List<User> users) {
        data.clear();
        data.addAll(users);
        rAdapter.notifyDataSetChanged();
    }
}
