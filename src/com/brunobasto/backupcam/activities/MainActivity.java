package com.brunobasto.backupcam.activities;

import com.brunobasto.backupcam.services.LookForWifiIntentService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        Intent lookForWifiIntentService = new Intent(this, LookForWifiIntentService.class);

		startService(lookForWifiIntentService);

		finish();
    }

}
