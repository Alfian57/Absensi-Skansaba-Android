package com.postingan.absenssiswasmkn1bantul.Model;

import com.google.gson.annotations.SerializedName;

public class Attendance {

	@SerializedName("present_date")
	private String presentDate;

	@SerializedName("present_time")
	private String presentTime;

	@SerializedName("desc")
	private String desc;

	public String getPresentDate(){
		return presentDate;
	}

	public String getPresentTime(){
		return presentTime;
	}

	public String getDesc(){
		return desc;
	}
}