package net.dzyga.android.netronicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import net.dzyga.android.netronicapp.R;
import net.dzyga.android.netronicapp.fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton)findViewById(R.id.main_left_btn)).setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_holder, new ListFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()>0) getSupportFragmentManager().popBackStack();
        else super.onBackPressed();
    }

    public void setToolbar(boolean close) {
        ((ImageButton)findViewById(R.id.main_left_btn)).setImageResource(close?R.drawable.close:R.drawable.back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_left_btn:
                onBackPressed();
                break;
        }
    }
}
