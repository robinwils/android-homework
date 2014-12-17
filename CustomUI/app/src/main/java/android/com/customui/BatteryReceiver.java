package android.com.customui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Julian on 17/12/2014.
 */
public class BatteryReceiver extends BroadcastReceiver {
    public int batteryLevel;

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        if (arg1.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_LOW)
                || arg1.getAction().equalsIgnoreCase(
                Intent.ACTION_BATTERY_CHANGED)
                || arg1.getAction().equalsIgnoreCase(
                Intent.ACTION_BATTERY_OKAY)) {
            int level = arg1.getIntExtra("level", 0);
            batteryLevel = level;
        }
    }



    public int getLevel()
    {
        return batteryLevel;
    }
}
