package android.com.customui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private DateTile dateView;
    private Handler mHandler = new Handler();
    private BatteryReceiver br;
    private TextView batteryLevel;
    private TextView batteryStatus;
    Intent batteryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView phoneView = (ImageView) findViewById(R.id.phone);
        ImageView lockView = (ImageView) findViewById(R.id.lock);
        this.dateView = (DateTile) findViewById(R.id.date);
        ImageView messageView = (ImageView) findViewById(R.id.message);
        ImageView cameraView = (ImageView) findViewById(R.id.camera);
        ImageView internetView = (ImageView) findViewById(R.id.internet);
        ImageView musicView = (ImageView) findViewById(R.id.music);
        final SimpleDateFormat df = new SimpleDateFormat("EEE dd-MM-yyyy hh:mm aaa");
        this.batteryLevel = (TextView) findViewById(R.id.percent);
        this.batteryStatus = (TextView) findViewById(R.id.charge);


        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "PHONE", Toast.LENGTH_SHORT).show();
            }
        });
        lockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "LOCK", Toast.LENGTH_SHORT).show();
            }
        });
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), df.format(c.getTime()), Toast.LENGTH_SHORT).show();
            }
        });
        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MESSAGE", Toast.LENGTH_SHORT).show();
            }
        });
        cameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CAMERA", Toast.LENGTH_SHORT).show();
            }
        });
        internetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "INTERNET", Toast.LENGTH_SHORT).show();
            }
        });
        musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MUSIC", Toast.LENGTH_SHORT).show();
            }
        });

        initBatteryListener();
        mHandler.postDelayed(updateTimerThread, 0);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(br);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            mHandler.postDelayed(this, 1000);
            dateView.invalidate();
            batteryLevel.setText(String.valueOf( br.getLevel() + "%"));
            batteryStatus.setText(determineChargingState());
        }
    };

    private void initBatteryListener()
    {
        br = new BatteryReceiver();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_LOW);
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        iFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        batteryIntent = registerReceiver(br, iFilter);
    }

    public String determineChargingState() {
        String res;

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, iFilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (status == BatteryManager.BATTERY_STATUS_CHARGING)
            res = "Charging";
        else if (status == BatteryManager.BATTERY_STATUS_FULL)
            res = "Charged";
        else
            res = "Disconnect";

        if (status ==  BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL)
        {
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB)
                res += " - USB";
            else if(chargePlug == BatteryManager.BATTERY_PLUGGED_AC)
                res += " - AC";
        }
        return (res);
    }
}
