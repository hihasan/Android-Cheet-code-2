//https://www.androhub.com/android-image-slider-using-viewpager/
//Bottom Sheet Behaviour - https://androidwave.com/bottom-sheet-behavior-in-android/
//Bottom Sheet Dialoug Fragment - https://androidwave.com/bottom-sheet-dialog-fragment-in-android/

package com.hihasan.project_s;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.viewpagerindicator.CirclePageIndicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private AppCompatButton design_bottom_sheet, design_bottom_sheet_dialoug;
    private CirclePageIndicator indicator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.bkash,R.drawable.bkash,R.drawable.bkash,R.drawable.bkash};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    TextView textViewBottomSheetState;
    Button toggleBottomSheet;
    BottomSheetBehavior bottomSheetBehavior;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

        initViews();

        // bind UI
        toggleBottomSheet = findViewById(R.id.design_bottom_sheet);
        //textViewBottomSheetState = findViewById(R.id.textViewState);
        // get the bottom sheet view
        ConstraintLayout bottomSheetLayout = findViewById(R.id.bottom_sheet);
        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Toast.makeText(getApplicationContext(),"STATE HIDDEN",Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Toast.makeText(getApplicationContext(),"STATE EXPANDED",Toast.LENGTH_SHORT).show();
                        // update toggle button text
                        Toast.makeText(getApplicationContext(),"Expand BottomSheet",Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Toast.makeText(getApplicationContext(),"STATE COLLAPSED",Toast.LENGTH_SHORT).show();
                        // update collapsed button text
                        Toast.makeText(getApplicationContext(),"Collapse BottomSheet",Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Toast.makeText(getApplicationContext(),"STATE DRAGGING",Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Toast.makeText(getApplicationContext(),"STATE SETTLING",Toast.LENGTH_SHORT).show();
                        break;
                }
                Log.d("TAG", "onStateChanged: " + newState);
            }
            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        // set listener on button click
        toggleBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    toggleBottomSheet.setText("Collapse BottomSheet");
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    toggleBottomSheet.setText("Expand BottomSheet");
                }
            }
        });
    }

    private void initViews() {
//        design_bottom_sheet = findViewById (R.id.design_bottom_sheet);
//        design_bottom_sheet_dialoug = findViewById (R.id.design_bottom_sheet_dialoug);
//
//        design_bottom_sheet.setOnClickListener(this);
//        design_bottom_sheet_dialoug.setOnClickListener(this);
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImageAdapter(MainActivity.this,ImagesArray));


        indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void onClick(View v) {
//        switch (v)
    }
}
