package com.brunobasto.backupcam.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.brunobasto.backupcam.R;
import com.brunobasto.backupcam.services.LookForWifiIntentService;

public class MainActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        
        Log.i("MainActivity", "created.");
        
        Intent lookForWifiIntentService = new Intent(this, LookForWifiIntentService.class);

		startService(lookForWifiIntentService);

        setContentView(R.layout.main);

        Window window = getWindow();

		window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

}
