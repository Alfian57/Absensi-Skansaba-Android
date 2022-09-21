package com.postingan.absenssiswasmkn1bantul.Api.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.postingan.absenssiswasmkn1bantul.Model.Attendance;

public class GetAttendanceResponse{

	@SerializedName("data")
	private List<Attendance> data;

	@SerializedName("message")
	private String message;

	@SerializedName("errors")
	private Object errors;

	public List<Attendance> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public Object getErrors(){
		return errors;
	}
}