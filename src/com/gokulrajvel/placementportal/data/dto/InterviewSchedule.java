package com.gokulrajvel.placementportal.data.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InterviewSchedule {
  private int scheduleId;
  private int companyId;
  private Date scheduleDate;
  private String scheduleTime;
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

  public InterviewSchedule() {}

  public void setScheduleId(int scheduleId) {
    this.scheduleId = scheduleId;
  }

  public int getScheduleId() {
    return scheduleId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  public int getCompanyId() {
    return companyId;
  }

  public void setScheduleDate(Date scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  public String getScheduleTime() {
    return scheduleTime;
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
    if (scheduleDate == null) {
      return "N/A";
    }
    return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(scheduleDate);
  }
}
