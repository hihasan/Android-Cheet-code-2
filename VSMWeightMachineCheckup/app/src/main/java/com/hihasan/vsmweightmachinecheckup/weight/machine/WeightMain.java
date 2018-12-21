package com.hihasan.vsmweightmachinecheckup.weight.machine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hihasan.vsmweightmachinecheckup.R;

import java.util.ArrayList;
import java.util.List;

public class WeightMain extends AppCompatActivity
{
    public AppCompatSpinner arotdar_selection, arot_number_selection_method, specific_problem;
    public AppCompatEditText comment;
    public AppCompatButton submit;
    public String arotdar_selection_value, arot_number_selection_method_value,specific_problem_value;
    int pos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        arotdar_selection=(AppCompatSpinner) findViewById (R.id.arotdar_selection);
        arot_number_selection_method=(AppCompatSpinner) findViewById (R.id.arot_number_selection_method);
        specific_problem=(AppCompatSpinner) findViewById (R.id.specific_problem);

        arotdar_selection.setSelected(true);
        arotdar_selection_value= arotdar_selection.getSelectedItem().toString();

        comment=(AppCompatEditText) findViewById (R.id.comment);
        submit=(AppCompatButton) findViewById (R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),arotdar_selection_value,Toast.LENGTH_SHORT).show();
                System.out.println("arotdar_selection value"+ arotdar_selection_value);
            }
        });

    }

}
