package com.brunobasto.backupcam.receivers;

import com.brunobasto.backupcam.services.LookForWifiIntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStartReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent lookForWifiIntentService = new Intent(context, LookForWifiIntentService.class);
		
		context.startService(lookForWifiIntentService);
	}

}
