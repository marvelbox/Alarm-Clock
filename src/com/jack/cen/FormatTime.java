package com.jack.cen;

public class FormatTime {
	public static String format(int h, int m)
	{
		String hour;
		String second;
		if(h < 10)
		{
			hour = "0" +  h;
		}
		else
		{
			hour = "" + h;
		}
		if(m < 10)
		{
			second = "0" + m;
		}
		else
		{
			second = "" + m;
		}
		return hour + ":" + second;
	}

}
