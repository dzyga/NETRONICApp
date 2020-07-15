package net.dzyga.android.netronicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.model.User;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> list;
    
    public UsersAdapter(Context c, List<User> data) {
        super(c, R.layout.item, data);
        context = c;
        list = data;
    }

    private static class ViewHolder {
        TextView name;
        ImageView image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final User item = list.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
            convertView.setTag(viewHolder);
            viewHolder.name = convertView.findViewById(R.id.item_text);
            viewHolder.image = convertView.findViewById(R.id.item_photo);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(item.getName());
        Picasso.get().load(item.getThumbnail()).noFade().into(viewHolder.image);
        return convertView;
    }

}
