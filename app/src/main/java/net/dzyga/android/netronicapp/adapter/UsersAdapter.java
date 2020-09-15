package net.dzyga.android.netronicapp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.fragment.UserFragment;
import net.dzyga.android.netronicapp.model.User;
import org.jetbrains.annotations.NotNull;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private final FragmentActivity activity;
    private List<User> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_text) TextView name;
        @BindView(R.id.item_photo) ImageView image;
        @BindView(R.id.item_layout) LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_layout)
        public void onItemClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                UserFragment fr = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                fr.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().addToBackStack("list")
                        .replace(R.id.main_fragment_holder, fr).commit();

            }
        }
    }

    public UsersAdapter(FragmentActivity activity, List<User> data) {
        this.activity = activity;
        list = data;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final User user = list.get(position);
        holder.name.setText(user.getName());
        Picasso.get().load(user.getThumbnail()).noFade().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
