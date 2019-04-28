package com.hihasan.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText name, phone, email;
    AppCompatButton submit,clear,retrive;

    public static final String MyPreferences="hihasan";
    public static final String Name="name";
    public static final String Phone="phone";
    public static final String Email="email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name=(AppCompatEditText) findViewById (R.id.name);
        phone=(AppCompatEditText) findViewById (R.id.phone);
        email=(AppCompatEditText) findViewById (R.id.email);

        submit=(AppCompatButton) findViewById (R.id.submit);
        clear=(AppCompatButton) findViewById (R.id.clear);
        retrive=(AppCompatButton) findViewById (R.id.retrive);
        final SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String ph=phone.getText().toString();
                String e=email.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putString(Name,n);
                editor.putString(Phone,ph);
                editor.putString(Email,e);

                editor.commit();
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();

            }
        });

        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String nValue, phValue,eValue;
                String nameValue=sharedPreferences.getString(Name,"");
                String phoneValue=sharedPreferences.getString(Phone,"");
                String emailValue=sharedPreferences.getString(Email,"");

                name.setText(nameValue);
                phone.setText(phoneValue);
                email.setText(emailValue);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.getText().clear();
                phone.getText().clear();
                email.getText().clear();
            }
        });


    }
}
