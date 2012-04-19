package com.jack.cen;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ring extends Activity{
	
	public MediaPlayer mediaPlayer = null;
	private Button cancelBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        mediaPlayer = MediaPlayer.create(this, R.raw.kexi);
        mediaPlayer.start();
        //cancelBtn = (Button)findViewById(R.id.cancelBtn);
        
        cancelBtn.setOnClickListener(new Button.OnClickListener(){
        	@Override
        	public void onClick(View view)
        	{
        		Intent intent = new Intent(Ring.this, AlarmReceiver.class);
        		PendingIntent pendingIntent = PendingIntent.getBroadcast(Ring.this, 0, intent, 0);
        		//获取闹钟管理器
        		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        		alarmManager.cancel(pendingIntent);
        		mediaPlayer.stop();
        		//finish();
        		System.exit(0);
        	}
        });
        
    }

}
