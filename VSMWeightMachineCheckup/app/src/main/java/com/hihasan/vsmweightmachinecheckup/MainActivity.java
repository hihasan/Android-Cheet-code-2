package com.hihasan.vsmweightmachinecheckup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hihasan.vsmweightmachinecheckup.change.machine.ChangeMain;
import com.hihasan.vsmweightmachinecheckup.printer.machine.PrinterMain;
import com.hihasan.vsmweightmachinecheckup.system.change.SystemMain;
import com.hihasan.vsmweightmachinecheckup.utils.CustomList;
import com.hihasan.vsmweightmachinecheckup.weight.machine.WeightMain;

public class MainActivity extends AppCompatActivity
{
    private FloatingActionButton fab;
    private ListView list;
    private Context context=this;

    //String to view
    String[] web = {
            "Weight Machine Health Checkup",
            "Printer Status",
            "Request To Change Parts",
            "System Questions"
    } ;

    String[] web2 = {
            "Description will goes here",
            "Description will goes here",
            "Description will goes here",
            "Description will goes here",
    } ;


    //Image to view
    Integer[] imageId = {
            R.drawable.baseline_devices_black_18dp,
            R.drawable.baseline_print_black_18dp,
            R.drawable.baseline_add_to_queue_black_18dp,
            R.drawable.baseline_face_black_18dp

    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=(FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(getApplicationContext(),"Insert", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Insert Value",Toast.LENGTH_SHORT).show();
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_helpline);
                dialog.setTitle("Title...");

                CardView first=(CardView) dialog.findViewById (R.id.first_contact);
                first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:01711438380"));
                        startActivity(intent);

                    }
                });

                CardView second=(CardView) dialog.findViewById(R.id.second_contact);
                second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:01737512549"));
                        startActivity(intent);
                    }
                });

                AppCompatButton close=(AppCompatButton) dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        CustomList adapter = new CustomList(MainActivity.this, web, web2, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        //List Adapter
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0)
                {
                    Toast.makeText(getApplicationContext(),"Weight Scale",Toast.LENGTH_SHORT).show();
                    Intent n=new Intent(MainActivity.this, WeightMain.class);
                    startActivity(n);
                }

                else if (position==1)
                {
                    Toast.makeText(getApplicationContext(),"Printer",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this, PrinterMain.class);
                    startActivity(i);
                }

                else if (position==2)
                {
                    Toast.makeText(getApplicationContext(),"Change Part",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this, ChangeMain.class);
                    startActivity(i);
                }

                else if (position==3)
                {
                    Toast.makeText(getApplicationContext(),"System Query",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(MainActivity.this, SystemMain.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"There might be some error in the system",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
