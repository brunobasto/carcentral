package com.brunobasto.backupcam.services;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import com.brunobasto.backupcam.receivers.WifiBroadcastReceiver;


public class LookForWifiIntentService extends Service {
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);

		if (wifi.isWifiEnabled() == false) {
            wifi.setWifiEnabled(true);
        }

		registerReceiver(new WifiBroadcastReceiver(wifi), new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

		wifi.startScan();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private WifiManager wifi;

}
