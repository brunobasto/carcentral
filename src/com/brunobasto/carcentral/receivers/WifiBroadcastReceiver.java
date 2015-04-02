package com.brunobasto.carcentral.receivers;

import java.util.List;

import com.brunobasto.carcentral.activities.MainActivity;
import com.brunobasto.carcentral.globals.PropertyUtil;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiBroadcastReceiver extends BroadcastReceiver {

	public WifiBroadcastReceiver(WifiManager wifi) {
		_wifi = wifi;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!isPowerPlugged()) {
			Log.i("WifiBroadcastReceiver", "Power not plugged. Going to sleep.");
			
			return;
		}

		List<ScanResult> results = _wifi.getScanResults();

		for (ScanResult result : results) {
			if (result.SSID.equals("Liferay8F")) {
				Log.i("WifiBroadcastReceiver", "Found Liferay8F");

				if (!isAppRunning(context, MainActivity.class.getName())) {
					Log.i("WifiBroadcastReceiver", "Opening local activity.");

					Intent appIntent = new Intent(context, MainActivity.class);

					appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);

					context.startActivity(appIntent);
				}
			}
		}

		_wifi.startScan();
	}
	
	protected boolean isAppRunning(Context context, String className) {
		ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();

        for (int i = 0; i < procInfos.size(); i++) {
        	RunningAppProcessInfo app = procInfos.get(i);

        	String name = app.processName;
        	
            if ((className.indexOf(name) == 0) &&
            	app.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

            	return true;
            }
        }

        return false;
	}

	protected boolean isPowerPlugged() {
		Boolean powerPlugged = (Boolean)PropertyUtil.getProperty("power.plugged");

		if (powerPlugged == null) {
			return false;
		}

		return powerPlugged.booleanValue();
	}

    private WifiManager _wifi;

}
