package com.postingan.absenssiswasmkn1bantul.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("time_start")
    @Expose
    private String timeStart;
    @SerializedName("time_finish")
    @Expose
    private String timeFinish;
    @SerializedName("gradeName")
    @Expose
    private String gradeName;
    @SerializedName("subjectName")
    @Expose
    private String subjectName;

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
