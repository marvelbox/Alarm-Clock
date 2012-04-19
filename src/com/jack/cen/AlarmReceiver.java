package com.jack.cen;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
/*		Log.d(Alarm.TAG, "the time is up, start the alarm...");
		Toast.makeText(context, "闹钟时间到了！", Toast.LENGTH_SHORT).show();*/
		intent.setClass(context, Ring.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);

	}
	
}
