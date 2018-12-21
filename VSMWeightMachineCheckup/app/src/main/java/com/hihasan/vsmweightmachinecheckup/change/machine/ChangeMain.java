package com.hihasan.vsmweightmachinecheckup.change.machine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;

import com.hihasan.vsmweightmachinecheckup.R;

import java.util.ArrayList;
import java.util.List;

public class ChangeMain extends AppCompatActivity
{
    public AppCompatSpinner arotdar_selection, arot_number_selection_method, specific_problem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        arotdar_selection_method();
        arot_number_selection_method();
        find_problem();
    }

    public void arotdar_selection_method(){
        arotdar_selection=(AppCompatSpinner) findViewById (R.id.arotdar_selection);
        List<String> list = new ArrayList<String>();
        list.add("Select Arot Types");
        list.add("Horticulture");
        list.add("Aquaculture");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arotdar_selection.setAdapter(dataAdapter);

    }

    public void arot_number_selection_method(){
        arot_number_selection_method=(AppCompatSpinner) findViewById (R.id.arot_number_selection_method);
        List<String> list = new ArrayList<String>();
        list.add("Select Arot Number");
        list.add("H1");
        list.add("H2");
        list.add("A1");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arot_number_selection_method.setAdapter(dataAdapter);

    }

    public void arot_information(){


    }

    public void find_problem(){
        specific_problem=(AppCompatSpinner) findViewById (R.id.specific_problem);
        List<String> list = new ArrayList<String>();
        list.add("Find Specific Problem");
        list.add("Wrong Weight");
        list.add("Wrong machine is showing too much weight");
        list.add("Continous Data");
        list.add("Others");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specific_problem.setAdapter(dataAdapter);

    }
}
