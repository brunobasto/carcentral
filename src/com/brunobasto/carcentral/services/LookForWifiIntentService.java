package com.brunobasto.carcentral.services;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import com.brunobasto.carcentral.receivers.BatteryStatusReceiver;
import com.brunobasto.carcentral.receivers.WifiBroadcastReceiver;


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
		
		WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);

		registerPowerReceiver(wifiManager);
		registerWifiReceiver(wifiManager);
	}

	protected void registerPowerReceiver(WifiManager wifiManager) {
		BatteryStatusReceiver batteryStatusReceiver = new BatteryStatusReceiver(wifiManager);

		registerReceiver(batteryStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}

	protected void registerWifiReceiver(WifiManager wifiManager) {
		if (wifiManager.isWifiEnabled() == false) {
			wifiManager.setWifiEnabled(true);
        }

		registerReceiver(new WifiBroadcastReceiver(wifiManager), new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

		wifiManager.startScan();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
