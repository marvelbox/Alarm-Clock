package com.jack.cen;


import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


public class Edit extends Activity {
	public static String TAG = "Alarm";
	public String value = "";
	public final Calendar cal = Calendar.getInstance();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit);
        

		
        Button exitBtn = (Button)findViewById(R.id.back_button);
        Button addBtn = (Button)findViewById(R.id.new_button);
        Button titleBtn = (Button)findViewById(R.id.editTitle);
        Button timeBtn = (Button)findViewById(R.id.selectTime);
        Button dateBtn = (Button)findViewById(R.id.selectWeek);

        final TextView showTime = (TextView)findViewById(R.id.showTime);
        final TextView showTitle = (TextView)findViewById(R.id.showTitle);
        
        exitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        addBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
/*				Intent intent = new Intent(Edit.this, AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(Edit.this, 0, intent, 0);
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);*/
				finish();
			}
        	
        });
        titleBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(Edit.this);
				LayoutInflater factory = LayoutInflater.from(Edit.this);
				final View textEntryView = factory.inflate(R.layout.title_dialog, null);
				builder.setTitle("请输入Title");
				builder.setView(textEntryView);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText title = (EditText)textEntryView.findViewById(R.id.title_edittext);
						showTitle.setText(title.getText().toString());
					}
				});
				builder.setNegativeButton("取消", null);
				builder.show();
			}
        	
        });
        timeBtn.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View arg0)
        	{
        		//final Calendar cal = Calendar.getInstance();
        		cal.setTimeInMillis(System.currentTimeMillis());
        		new TimePickerDialog(Edit.this, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker arg0, int h, int m) {
						// TODO Auto-generated method stub
						showTime.setText(FormatTime.format(h, m));
						//设置日历的时间，主要是让日历的年月日和当前同步
						cal.setTimeInMillis(System.currentTimeMillis());
						cal.set(Calendar.HOUR_OF_DAY, h);
						cal.set(Calendar.MINUTE, m);
						//将秒和毫秒设置为0
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
			
						}
				}, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
        	}        	
        });
        
        dateBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	    		Intent intent = new Intent();
	    		intent.setClass(Edit.this, Week.class);
	    		Edit.this.startActivityForResult(intent, 0);
			}
        	
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
    	   switch (resultCode) {
    	   case RESULT_OK:
    		   final TextView showDate = (TextView)findViewById(R.id.showDate);
    		   value = intent.getStringExtra("week");
   				if (value != null)
   				{
   					showDate.setText(value);
   				}
    	       break;
    	  default:
    	        break;
    	  }
    	  }
}