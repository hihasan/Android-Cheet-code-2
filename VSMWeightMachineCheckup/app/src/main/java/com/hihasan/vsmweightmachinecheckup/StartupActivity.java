package com.hihasan.vsmweightmachinecheckup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class StartupActivity extends AppCompatActivity
{
    public static final int APP_LOAD_TIME=1000;
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        logo=(ImageView) findViewById (R.id.startup_img);
        logoAnimation();


        //Thread Handler for 5 sec.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConnection();
            }
        }, APP_LOAD_TIME);
    }

    public void logoAnimation(){
        Animation animator= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        logo.startAnimation(animator);
    }

    //Check connection Here
    private void checkConnection()
    {
        if(isOnline())
        {
            Toast.makeText(getApplicationContext(), "You are connected to Internet", Toast.LENGTH_SHORT).show();
            final Intent mainIntent = new Intent(StartupActivity.this, MainActivity.class);
            StartupActivity.this.startActivity(mainIntent);
            StartupActivity.this.finish();



        }

        else
        {
            Toast.makeText(getApplicationContext(), "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            final Intent mainIntent = new Intent(StartupActivity.this, NoInternetActivity.class);
            StartupActivity.this.startActivity(mainIntent);
            StartupActivity.this.finish();
        }
    }

    //Internet Connection
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
