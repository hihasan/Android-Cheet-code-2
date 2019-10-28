package com.hihasan.readwrite;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    TextView response;
    Button saveButton, readButton;
    Context context=this;

    private String FILENAME = "SampleFile.txt";
    private String DNAME = "Hihasan";
    File myExternalFile;
    String myData = "";
    public Boolean isStoragePermissionGranted=true;
    public static int  REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExternalStorageWritable("Abul");

            }

        });
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable(String fname) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            String myfolder=Environment.getExternalStorageDirectory()+"/"+fname;
            File f=new File(myfolder);
            if(!f.exists())
            {
                if(!f.mkdir()){
                    Toast.makeText(this, myfolder+" can't be created.", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(this, myfolder+" can be created.", Toast.LENGTH_SHORT).show();

            }

            else
                Toast.makeText(this, myfolder+" already exits.", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(getApplicationContext(),"Not Writeable",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION) {
            int grantResultsLength = grantResults.length;
            if (grantResultsLength > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You grant write external storage permission. Please click original button again to continue.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "You denied write external storage permission.", Toast.LENGTH_LONG).show();
            }
        }
    }
}


