package com.brunobasto.carcentral.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;

import com.brunobasto.carcentral.globals.PropertyUtil;

public class BatteryStatusReceiver extends BroadcastReceiver {

	public BatteryStatusReceiver(WifiManager wifiManager) {
		_wifiManager = wifiManager;

		PropertyUtil.setProperty("power.plugged", false);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

		int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

		boolean isPlugged = isCharging || acCharge || usbCharge;

		PropertyUtil.setProperty("power.plugged", isPlugged);

		if (isPlugged) {
			_wifiManager.startScan();
		}
	}
	
	private WifiManager _wifiManager;

}
