package com.brunobasto.backupcam.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.brunobasto.backupcam.globals.PropertyUtil;

public class BatteryStatusReceiver extends BroadcastReceiver {

	public BatteryStatusReceiver() {
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
	}

}
