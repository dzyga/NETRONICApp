package net.dzyga.android.netronicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.model.User;
import net.dzyga.android.netronicapp.viewmodel.UsersViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends BaseFragment {
    @BindView(R.id.user_name) TextView name;
    @BindView(R.id.user_cell) TextView cell;
    @BindView(R.id.user_phone) TextView phone;
    @BindView(R.id.user_gender) TextView gender;
    @BindView(R.id.user_nat) TextView nat;
    @BindView(R.id.user_image) ImageView image;

    private UsersViewModel usersViewModel;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        ButterKnife.bind(this, rootView);
        final int position = getArguments().getInt("position", 0);
        usersViewModel.getUsers().observe(getViewLifecycleOwner(), users -> updateUser(users, position));
        return rootView;
    }

    private void updateUser(List<User> users, int position) {
        if (users!=null && !users.isEmpty()) {
            User user = users.get(position);
            if (user != null) {
                name.setText(user.getName());
                cell.setText(user.cell);
                phone.setText(user.phone);
                gender.setText(user.gender);
                nat.setText(user.nat);
                Picasso.get().load(user.getPicture()).noFade().into(image);
            }
        }
    }
}
