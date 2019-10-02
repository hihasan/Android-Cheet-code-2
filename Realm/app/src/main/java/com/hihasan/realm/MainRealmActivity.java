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
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

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
                addEmployee();
                break;
            case R.id.btnRead:
                ReadEmployes();
                break;
            case R.id.btnUpdate:
                updateEmployeeRecords();
                break;
            case R.id.btnFilterByAge:
                filterByAge();
                break;
            case R.id.btnDelete:
                deleteEmployeeRecord();
                break;
            case R.id.btnDeleteWithSkill:
                deleteEmployeeWithSkill();
                break;

        }

    }

    private void addEmployee() {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {


                    try {


                        if (!inName.getText().toString().trim().isEmpty()) {
                            Employee employee = new Employee();
                            employee.name = inName.getText().toString().trim();

                            if (!inAge.getText().toString().trim().isEmpty())
                                employee.age = Integer.parseInt(inAge.getText().toString().trim());


                            String languageKnown = inSkill.getText().toString().trim();

                            if (!languageKnown.isEmpty()) {
                                Skill skill = realm.where(Skill.class).equalTo(Skill.PROPERTY_SKILL, languageKnown).findFirst();

                                if (skill == null) {
                                    skill = realm.createObject(Skill.class, languageKnown);
                                    realm.copyToRealm(skill);
                                }

                                employee.skills = new RealmList<>();
                                employee.skills.add(skill);
                            }

                            realm.copyToRealm(employee);
                        }

                    } catch (RealmPrimaryKeyConstraintException e) {
                        Toast.makeText(getApplicationContext(), "Primary Key exists, Press Update instead", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    private void ReadEmployes(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Employee> results = realm.where(Employee.class).findAll();
                textView.setText("");
                for (Employee employee : results) {
                    textView.append(employee.name + " age: " + employee.age + " skill: " + employee.skills.size());
                }
            }
        });
    }

    private void updateEmployeeRecords() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                if (!inName.getText().toString().trim().isEmpty()) {


                    Employee employee = realm.where(Employee.class).equalTo(Employee.PROPERTY_NAME, inName.getText().toString()).findFirst();
                    if (employee == null) {
                        employee = realm.createObject(Employee.class, inName.getText().toString().trim());
                    }
                    if (!inAge.getText().toString().trim().isEmpty())
                        employee.age = Integer.parseInt(inAge.getText().toString().trim());

                    String languageKnown = inSkill.getText().toString().trim();
                    Skill skill = realm.where(Skill.class).equalTo(Skill.PROPERTY_SKILL, languageKnown).findFirst();

                    if (skill == null) {
                        skill = realm.createObject(Skill.class, languageKnown);
                        realm.copyToRealm(skill);
                    }


                    if (!employee.skills.contains(skill))
                        employee.skills.add(skill);

                }
            }
        });
    }

    private void deleteEmployeeRecord() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Employee employee = realm.where(Employee.class).equalTo(Employee.PROPERTY_NAME, inName.getText().toString()).findFirst();
                if (employee != null) {
                    employee.deleteFromRealm();
                }
            }
        });
    }

    private void deleteEmployeeWithSkill() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<Employee> employees = realm.where(Employee.class).equalTo("skills.skillName", inSkill.getText().toString().trim()).findAll();
                employees.deleteAllFromRealm();
            }
        });
    }


    private void filterByAge() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<Employee> results = realm.where(Employee.class).greaterThanOrEqualTo(Employee.PROPERTY_AGE, 25).findAllSortedAsync(Employee.PROPERTY_NAME);

                txtFilterByAge.setText("");
                for (Employee employee : results) {
                    txtFilterByAge.append(employee.name + " age: " + employee.age + " skill: " + employee.skills.size());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}
