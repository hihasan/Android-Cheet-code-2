package com.hihasan.expandabletext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import at.blogc.android.views.ExpandableTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExpandableTextView expandableTextView = (ExpandableTextView) this.findViewById(R.id.expandableTextView);
        final Button buttonToggle = (Button) this.findViewById(R.id.button_toggle);

        expandableTextView.setAnimationDuration(750L);

        expandableTextView.setInterpolator(new OvershootInterpolator());

        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());

        buttonToggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                buttonToggle.setText(expandableTextView.isExpanded() ? R.string.expand : R.string.close);
                expandableTextView.toggle();
            }
        });

        buttonToggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                if (expandableTextView.isExpanded()){
                    expandableTextView.collapse();
                    buttonToggle.setText(R.string.expand);
                }else{
                    expandableTextView.expand();
                    buttonToggle.setText(R.string.close);
                }
            }
        });

    }
}
