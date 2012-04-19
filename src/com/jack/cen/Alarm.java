package com.jack.cen;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Alarm extends Activity {
	public static String TAG = "Alarm";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        Button exitBtn = (Button)findViewById(R.id.back_button);
        Button addBtn = (Button)findViewById(R.id.new_button);
        //退出按钮
        exitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        //添加新的闹钟
        addBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	    		Intent intent = new Intent();
	    		intent.setClass(Alarm.this, Edit.class);
	    		Alarm.this.startActivity(intent);
			}
        	
        });
        //已添加的闹钟列表
        final ListView list = (ListView)findViewById(R.id.alarmclock_listview);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i = 0; i < 6; i++)
        {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemTitle", "Get Up");
        	map.put("ItemTime","07:30");
        	map.put("ItemDate", "Mon, Tues, Wed, Thurs, Fri, Sat, Sun");
        	listItem.add(map);
        }
        
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem, R.layout.list_items,
        		new String[] {"ItemTitle","ItemTime","ItemDate"},
        		new int[] {R.id.item_title, R.id.item_time, R.id.item_date});
        
        list.setAdapter(listItemAdapter);
        //点击listitem跳转到编辑界面
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
	    		Intent intent = new Intent();
	    		intent.setClass(Alarm.this, Edit.class);
	    		Alarm.this.startActivity(intent);
			}
        	
        });
        //长按listitem可进行删除和关闭操作
        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Alarm.this);
				builder.setTitle("请选择要操作选项！");
				builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.setNeutralButton("关闭", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.create().show();
			}
        	
        });
        
        /*timeBtn = (Button)findViewById(R.id.timeBtn);
        cancelBtn = (Button)findViewById(R.id.cancelAlarmBtn);
        
        timeBtn.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View arg0)
        	{
        		Log.d(TAG, "click the time button to set time");
        		final Calendar cal = Calendar.getInstance();
        		cal.setTimeInMillis(System.currentTimeMillis());
        		new TimePickerDialog(Alarm.this, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker arg0, int h, int m) {
						// TODO Auto-generated method stub
						//更新按钮上的时间
						timeBtn.setText(h + " : " + m);
						//设置日历的时间，主要是让日历的年月日和当前同步
						cal.setTimeInMillis(System.currentTimeMillis());
						cal.set(Calendar.HOUR_OF_DAY, h);
						cal.set(Calendar.MINUTE, m);
						//将秒和毫秒设置为0
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						//建立Intent和PendingIntent来调用闹钟管理器
						
						 *public Intent (Context packageContext, Class<?> cls) 
						 *Create an intent for specific component. All other fields(action, data, type, class) are null,
						 *though they can be modified later with explicit calls.
						 *This provides a convenient way to create an intent that is intended to execute a hard-coded class name,
						 *rather than relying on the system to find an appropriate class for you
						 *
						 *Parameters
						 *packageContext		A Context of the application package implementing this class
						 *cls					The component class that is to be used for the intent
						 
						Intent intent = new Intent(Alarm.this, AlarmReceiver.class);
						
						 * public static PendingIntent getBroadcast(Context context, int requestCode, Intent intent, int flags)
						 * Retrieve a PendingIntent that will perform a broadcast, like calling Context.sendBroadcast().
						 * 
						 * Parameters
						 * context				The Context in which this PendingIntent should perform the broadcast.
						 * requestCode			Private request code for the sender(currently not used)
						 * intent				The Intent to be broadcast
						 * flags 				May be FLAG_ONE_SHOT,FLAG_NO_CREATE,FLAG_CANCEL_CURRENT,FLAG_UPDATE_CURRENT, 
						 * 						or any of the flags as supported by Intent.fillIn() to control which unspecified parts of 
						 * 						the intent that can be supplied when the actual send happens.
						 * 
						 * Returns
						 * Returns an existing or new PendingIntent matching the given parameters. May return null only if FLAG_NO_CREATE has been supplied
						 
						PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, intent, 0);
						//获取闹钟管理器
						AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
						//设置闹钟
						alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
						//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 10*1000, pendingIntent);
						Toast.makeText(Alarm.this, "设置闹钟的时间为： "+String.valueOf(h)+":"+String.valueOf(m), Toast.LENGTH_SHORT).show();
						Log.d(TAG, "set the time ");
						}
				}, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
        	}
        	
        });
        //取消闹钟按钮事件监听
        cancelBtn.setOnClickListener(new Button.OnClickListener(){
        	@Override
        	public void onClick(View view)
        	{
        		Intent intent = new Intent(Alarm.this, AlarmReceiver.class);
        		PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, intent, 0);
        		//获取闹钟管理器
        		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        		alarmManager.cancel(pendingIntent);
        		Toast.makeText(Alarm.this, "闹钟已经取消", Toast.LENGTH_SHORT).show();
        	}
        });*/
    }
}