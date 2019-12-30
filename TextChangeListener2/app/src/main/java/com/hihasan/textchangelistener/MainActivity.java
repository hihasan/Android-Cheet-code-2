package com.hihasan.textchangelistener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextWatcher tv1;
    private TextWatcher tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);


        tv1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                double x = Double.valueOf(et1.getText().toString());
                et2.removeTextChangedListener(tv2); // "disable" the watcher
                et2.setText(String.valueOf((x*2)));
                et2.addTextChangedListener(tv2);    // "enable" the watcher
            }
        };



        tv2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                double x = Double.valueOf(et2.getText().toString());
                et1.removeTextChangedListener(tv1); // "disable" the watcher
                et1.setText(String.valueOf((x/2)));
                et1.addTextChangedListener(tv1);    // "enable" the watcher
            }
        };

        et1.addTextChangedListener(tv1);
        et2.addTextChangedListener(tv2);
    }
}
