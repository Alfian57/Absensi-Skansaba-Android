package com.postingan.absenssiswasmkn1bantul.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleData {
    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = null;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
