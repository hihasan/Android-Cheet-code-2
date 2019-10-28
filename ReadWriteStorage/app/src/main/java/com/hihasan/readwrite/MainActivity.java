package com.hihasan.readwrite;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

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
//                Snackbar.make(view, "Action Required!!!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                getPrivateAlbumStorageDir(getApplicationContext(),"/hihasan folder2");
                isExternalStorageWritable();
                //isExternalStorageReadable();
            }

        });
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File file=new File(Environment.getExternalStorageState(),"/storage/extSdCard/Hakuna Matata");
            if (!file.exists()){
                file.mkdirs();
                Toast.makeText(getApplicationContext(),"FOlder Created",Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(getApplicationContext(),"Folder Exists",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        Toast.makeText(getApplicationContext(),"Not Writeable",Toast.LENGTH_SHORT).show();
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(getApplicationContext(),"Readable",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(getApplicationContext(),"Not Readable",Toast.LENGTH_SHORT).show();
        return false;


    }

    public File getPrivateAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.exists()){
            Log.e("Hihasan","FOlder Created");
            file.mkdirs();
            Toast.makeText(getApplicationContext(),"FOlder Created",Toast.LENGTH_SHORT).show();

        }
        else {
            Log.e("Hihasan","Folder Exists");
            Toast.makeText(getApplicationContext(),"Folder Exists",Toast.LENGTH_SHORT).show();
        }
//        if (!file.mkdirs()) {
//            Log.e("Hihasan", "Directory not created");
//        }
        return file;
    }
}


