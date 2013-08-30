package com.aadebuger.weathermap.util;

public class WeatherFilter {

	public static String  filterData(String data)
	{
		String[] datav = data.split(" ");
		System.out.println("datav length="+datav.length);
		if ( datav.length>=3)
		{
			return datav[2];
		}
		return data;
	}
}
