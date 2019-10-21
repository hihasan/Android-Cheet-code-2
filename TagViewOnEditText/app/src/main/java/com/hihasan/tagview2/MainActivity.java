package com.hihasan.tagview2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.originqiu.library.EditTag;

public class MainActivity extends AppCompatActivity implements SwitchCompat.OnCheckedChangeListener {

    private EditTag editTagView;

    private SwitchCompat statusSwitchView;

    private List<String> tagStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTagView = (EditTag) findViewById(R.id.edit_tag_view);
        statusSwitchView = (SwitchCompat) findViewById(R.id.status_switch);
        statusSwitchView.setOnCheckedChangeListener(this);

        for (int i = 0; i < 10; i++) {
            tagStrings.add("test" + i);
        }
        //Set tag add callback before set tag list
        editTagView.setTagAddCallBack(new EditTag.TagAddCallback() {
            @Override
            public boolean onTagAdd(String tagValue) {
                if ("test1".equals(tagValue)) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        editTagView.setTagDeletedCallback(new EditTag.TagDeletedCallback() {
            @Override
            public void onTagDelete(String deletedTagValue) {
                Toast.makeText(MainActivity.this, deletedTagValue, Toast.LENGTH_SHORT).show();
            }
        });
        editTagView.setTagList(tagStrings);

        editTagView.addTag("hello world!");
        editTagView.removeTag("test3");

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        editTagView.setEditable(isChecked);
    }
}