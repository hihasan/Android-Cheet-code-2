package com.hihasan.realm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class MainRealmActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnAdd,btnRead,btnUpdate,btnFilterByAge,btnDelete,btnDeleteWithSkill;
    private EditText inName,inAge,inSkill;
    private TextView textView, txtFilterBySkill, txtFilterByAge;
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        initViews();
    }

    private void initViews()
    {
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = findViewById (R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnUpdate = findViewById (R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnFilterByAge = findViewById (R.id.btnFilterByAge);
        btnFilterByAge.setOnClickListener(this);
        btnDelete = findViewById (R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnDeleteWithSkill = findViewById (R.id.btnDeleteWithSkill);
        btnDeleteWithSkill.setOnClickListener(this);

        inName = findViewById (R.id.inName);
        inAge = findViewById (R.id.inAge);
        inSkill = findViewById (R.id.inSkill);

        textView = findViewById (R.id.textViewEmployees);
        txtFilterBySkill = findViewById (R.id.txtFilterBySkill);
        txtFilterByAge = findViewById (R.id.txtFilterByAge);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                Toast.makeText(getApplicationContext(),"Action Required",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRead:
                Toast.makeText(getApplicationContext(),"Action required", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnUpdate:
                Toast.makeText(getApplicationContext(),"action Required", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnFilterByAge:
                Toast.makeText(getApplicationContext(),"Action Required", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                Toast.makeText(getApplicationContext(),"Action Required", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDeleteWithSkill:
                Toast.makeText(getApplicationContext(),"Action Required", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
