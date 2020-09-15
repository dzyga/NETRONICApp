package net.dzyga.android.netronicapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.fragment.ListFragment;
import net.dzyga.android.netronicapp.fragment.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.main_left_btn) ImageButton backButton;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager.beginTransaction().replace(R.id.main_fragment_holder, new ListFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount()>0) fragmentManager.popBackStack();
        else super.onBackPressed();
    }

    @OnClick(R.id.main_left_btn)
    void onBackClick(View v) {
        onBackPressed();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_fragment_holder);
        if (fragment!=null){
            if (fragment instanceof UserFragment) backButton.setImageResource(R.drawable.back);
            else backButton.setImageResource(R.drawable.close);
        }
    }
}
