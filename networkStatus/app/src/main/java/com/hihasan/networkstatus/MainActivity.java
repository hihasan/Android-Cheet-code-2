package com.hihasan.networkstatus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mRelativeLayout;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the application context
        MainActivity mContext = (MainActivity) getApplicationContext();

        // Change the action bar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF246AB4"))
        );

        mTextView = findViewById (R.id.tv);
        mRelativeLayout = findViewById (R.id.rl);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean locationState = isLocationEnabled();
                if(locationState){
                    mTextView.setText("Location Service Is Enabled.");

                }else {
                    mTextView.setText("Location Service Is Disabled.");
                }
            }
        });
    }

    // Better way to check location service status
    protected boolean isLocationEnabled(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            /*
                Settings.Secure
                    Secure system settings, containing system preferences that applications can read
                    but are not allowed to write. These are for preferences that the user must
                    explicitly modify through the system UI or specialized APIs for those values,
                    not modified directly by applications.
            */
            /*
                public static final String LOCATION_MODE
                    The degree of location access enabled by the user.

                    When used with putInt(ContentResolver, String, int), must be one of
                    LOCATION_MODE_HIGH_ACCURACY, LOCATION_MODE_SENSORS_ONLY,
                    LOCATION_MODE_BATTERY_SAVING, or LOCATION_MODE_OFF. When used with
                    getInt(ContentResolver, String), the caller must gracefully handle additional
                    location modes that might be added in the future.

                    Note: do not rely on this value being present in settings.db or on
                    ContentObserver notifications for the corresponding Uri.
                    Use MODE_CHANGED_ACTION to receive changes in this value.

                    Constant Value: "location_mode"
            */
            /*
                public static int getInt (ContentResolver cr, String name, int def)
                    Convenience function for retrieving a single secure settings value as an integer.
                    Note that internally setting values are always stored as strings; this function
                    converts the string to an integer for you. The default value will be returned
                    if the setting is not defined or not an integer.

                Parameters
                    cr : The ContentResolver to access.
                    name : The name of the setting to retrieve.
                    def : Value to return if the setting is not defined.
                Returns
                    The setting's current value, or 'def' if it is not defined or not a valid integer.
            */
            // check location state for api version 19 or greater
            int locationMode = Settings.Secure.getInt(
                    getApplicationContext().getContentResolver(),
                    Settings.Secure.LOCATION_MODE,
                    0
            );

            /*
                public static final int LOCATION_MODE_OFF
                    Location access disabled.

                Constant Value: 0 (0x00000000)
            */
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        }else{
            /*
                public static String getString (ContentResolver resolver, String name)
                    Look up a name in the database.

                Parameters
                    resolver : to access the database with
                    name : to look up in the table
                Returns
                    the corresponding value, or null if not present
            */
            /*
                public static final String LOCATION_PROVIDERS_ALLOWED
                    This constant was deprecated in API level 19.
                    use LOCATION_MODE and MODE_CHANGED_ACTION (or PROVIDERS_CHANGED_ACTION)

                    Comma-separated list of location providers that activities may access.
                    Do not rely on this value being present in settings.db or on ContentObserver
                    notifications on the corresponding Uri.

                    Constant Value: "location_providers_allowed"
            */
            String locationProviders = Settings.Secure.getString(
                    getApplicationContext().getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            );

            /*
                public static boolean isEmpty (CharSequence str)
                    Returns true if the string is null or 0-length.

                Parameters
                    str : the string to be examined
                Returns
                    true : if str is null or zero length
            */
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    // Custom method to check GPS service is enabled or disabled
    protected boolean isGPSEnabled(){
        /*
            LocationManager
                This class provides access to the system location services. These services allow
                applications to obtain periodic updates of the device's geographical location, or
                to fire an application-specified Intent when the device enters the proximity of
                a given geographical location.

                You do not instantiate this class directly; instead, retrieve it through
                Context.getSystemService(Context.LOCATION_SERVICE).
        */
        /*
            public abstract Object getSystemService (String name)
                Return the handle to a system-level service by name. The class of the returned
                object varies by the requested name.
        */
        /*
            public static final String LOCATION_SERVICE
                Use with getSystemService(Class) to retrieve a LocationManager for
                controlling location updates.

                Constant Value: "location"
        */
        LocationManager manager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        /*
            public boolean isProviderEnabled (String provider)
                Returns the current enabled/disabled status of the given provider.

                If the user has enabled this provider in the Settings menu, true is returned
                otherwise false is returned

                Callers should instead use LOCATION_MODE unless they depend on provider-specific
                APIs such as requestLocationUpdates(String, long, float, LocationListener).

                Before API version LOLLIPOP, this method would throw SecurityException if the
                location permissions were not sufficient to use the specified provider.

            Parameters
                provider : the name of the provider
            Returns
                true : if the provider exists and is enabled
            Throws
                IllegalArgumentException : if provider is null

        */
        boolean GPSStatus = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return GPSStatus;
    }

    // Custom method for api level 19 or higher to check location service status
    protected boolean isLocationEnabledFromAPI19(){
        int locationMode = Settings.Secure.getInt(
                getApplicationContext().getContentResolver(),
                Settings.Secure.LOCATION_MODE,
                0
        );

        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }
}
