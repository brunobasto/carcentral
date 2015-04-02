package com.brunobasto.carcentral.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.brunobasto.carcentral.services.LookForWifiIntentService;

public class AutoStartReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Start Wifi Service
		
		Intent lookForWifiIntentService = new Intent(context, LookForWifiIntentService.class);
		
		context.startService(lookForWifiIntentService);
	}

}
