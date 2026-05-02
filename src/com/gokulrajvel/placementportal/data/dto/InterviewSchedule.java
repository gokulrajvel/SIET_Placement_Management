package com.gokulrajvel.placementportal.data.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InterviewSchedule {
    private int schedule_id;
    private int companyId;
    private Date schedule_date;
    private String schedule_time;
    private String locationOrLink;
    private String studentEmail;
    private String teacherId;
    private InterviewStatus interviewStatus = InterviewStatus.SCHEDULED;
    private String remarks = "";

    public enum InterviewStatus {
        SCHEDULED,
        COMPLETED,
        SELECTED,
        REJECTED,
        CANCELLED
    }

    public InterviewSchedule() {

    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setSchedule_date(Date schedule_date) {
        this.schedule_date = schedule_date;
    }

    public Date getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public void setLocationOrLink(String locationOrLink) {
        this.locationOrLink = locationOrLink;
    }

    public String getLocationOrLink() {
        return locationOrLink;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getFormattedDate() {
        if (schedule_date == null) {
            return "N/A";
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(schedule_date);
    }
}
