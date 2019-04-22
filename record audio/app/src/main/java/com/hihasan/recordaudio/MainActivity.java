package com.hihasan.recordaudio;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private Context context=this;
    private AppCompatButton buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording ;
    private String AudioSavePathInDevice = null;
    private MediaRecorder mediaRecorder ;
    Random random ;
    private String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    private MediaPlayer mediaPlayer ;

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
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_record);

                buttonStart = (AppCompatButton) dialog.findViewById(R.id.button);
                buttonStop = (AppCompatButton) dialog.findViewById(R.id.button2);
                buttonPlayLastRecordAudio = (AppCompatButton) dialog.findViewById(R.id.button3);
                buttonStopPlayingRecording = (AppCompatButton) dialog.findViewById(R.id.button4);

                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(false);

                //btnStart()
                buttonStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(checkPermission()) {

                            AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                            MediaRecorderReady();

                            try {
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            buttonStart.setEnabled(false);
                            buttonStop.setEnabled(true);

                            Toast.makeText(MainActivity.this, "Recording started",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            requestPermission();
                        }

                    }
                });

                //btnStop()
                buttonStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaRecorder.stop();
                        buttonStop.setEnabled(false);
                        buttonPlayLastRecordAudio.setEnabled(true);
                        buttonStart.setEnabled(true);
                        buttonStopPlayingRecording.setEnabled(false);

                        Toast.makeText(MainActivity.this, "Recording Completed",
                                Toast.LENGTH_LONG).show();
                    }
                });

                //btnStartPlaying()
                buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) throws IllegalArgumentException,
                            SecurityException, IllegalStateException {

                        buttonStop.setEnabled(false);
                        buttonStart.setEnabled(false);
                        buttonStopPlayingRecording.setEnabled(true);

                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(AudioSavePathInDevice);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mediaPlayer.start();
                        Toast.makeText(MainActivity.this, "Recording Playing",
                                Toast.LENGTH_LONG).show();
                    }
                });

                //btnStopPlaying()
                buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonStop.setEnabled(false);
                        buttonStart.setEnabled(true);
                        buttonStopPlayingRecording.setEnabled(false);
                        buttonPlayLastRecordAudio.setEnabled(true);

                        if(mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            MediaRecorderReady();
                        }
                    }
                });


                dialog.show();
            }
        });
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"PermissionDenied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&  result1 == PackageManager.PERMISSION_GRANTED;
    }
}
