package com.brunobasto.backupcam.receivers;

import java.util.List;

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
		List<ScanResult> results = _wifi.getScanResults();

		for (ScanResult result : results) {
			if (result.SSID.equals("Liferay8F")) {
				Log.i("WifiBroadcastReceiver", "Found Liferay*F");

				String appName = "com.waze";

				if (!isAppRunning(context, appName)) {
					Log.i("WifiBroadcastReceiver", "Opening app.");

					Intent appIntent = new Intent();

					appIntent.setPackage(appName);

					appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);

					context.startActivity(appIntent);
				}
			}
		}

		_wifi.startScan();
	}

	protected boolean isAppRunning(Context context, String appName) {
		ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();

        for (int i = 0; i < procInfos.size(); i++) {
        	RunningAppProcessInfo app = procInfos.get(i);

        	String name = app.processName;

            if ((name.indexOf(appName) == 0) &&
            	app.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

            	return true;
            }
        }

        return false;
	}

    private WifiManager _wifi;

}
