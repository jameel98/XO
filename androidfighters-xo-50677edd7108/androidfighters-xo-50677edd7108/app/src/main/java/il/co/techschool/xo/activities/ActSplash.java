package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import il.co.techschool.xo.R;

public class ActSplash extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentLogin = new Intent(mContext, ActLogin.class);
                startActivity( intentLogin);

            }
        }, 1500);
    }

    private void initComponents(){
        setContentView(R.layout.act_splash);
    }
}
