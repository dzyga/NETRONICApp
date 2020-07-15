package net.dzyga.android.netronicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.activity.MainActivity;
import net.dzyga.android.netronicapp.adapter.UsersAdapter;
import net.dzyga.android.netronicapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private static final String TAG = "UserFragment";
    private TextView name, cell, phone, gender, nat;
    private ImageView image;
    private User user;

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
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        name = (TextView) rootView.findViewById(R.id.user_name);
        cell = (TextView) rootView.findViewById(R.id.user_cell);
        phone = (TextView) rootView.findViewById(R.id.user_phone);
        gender = (TextView) rootView.findViewById(R.id.user_gender);
        nat = (TextView) rootView.findViewById(R.id.user_nat);
        image = (ImageView) rootView.findViewById(R.id.user_image);

        if (user != null){
            name.setText(user.getName());
            cell.setText(user.cell);
            phone.setText(user.phone);
            gender.setText(user.gender);
            nat.setText(user.nat);
            Picasso.get().load(user.getPicture()).noFade().into(image);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbar(false);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
