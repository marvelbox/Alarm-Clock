package com.jack.cen;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Week extends Activity {
	public String week = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.week);
        Button exitBtn = (Button)findViewById(R.id.back_button);
        CheckBox Mon = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox Tues = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox Wed = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox Thurs = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox Fri = (CheckBox)findViewById(R.id.checkBox5);
        CheckBox Sat = (CheckBox)findViewById(R.id.checkBox6);
        CheckBox Sun = (CheckBox)findViewById(R.id.checkBox7);
        exitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	    		Intent intent = new Intent();
	    		intent.putExtra("week", week);
	    		intent.setClass(Week.this, Edit.class);
	    		//Week.this.startActivity(intent);
	    		Week.this.setResult(RESULT_OK, intent);
	    		System.out.println(week);
				finish();
			}
        	
        });
        Mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Mon ";
				}
			}
		});
        Tues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Tues ";
				}
			}
		});
        Wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Wed ";
				}
			}
		});
        Thurs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Thurs ";
				}
			}
		});
        Fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Fri ";
				}
			}
		});
        Sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Sat ";
				}
			}
		});
        Sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					week = week + "Sun ";
				}
			}
		});
    }     
}