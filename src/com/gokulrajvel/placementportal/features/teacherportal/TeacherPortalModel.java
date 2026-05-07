package com.gokulrajvel.placementportal.features.teacherportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import java.util.List;

class TeacherPortalModel {
  public TeacherPortalModel(TeacherPortalPresenter teacherPortalPresenter) {
    // Presenter kept for MVP parity.
  }

  List<StudentSignup> getStudents() {
    return PlacementSIETDB.getInstance().getStudentList();
  }

  List<CompanyDetails> getCompanies() {
    return PlacementSIETDB.getInstance().getCompanyList();
  }

  boolean addCompany(CompanyDetails companyDetails) {
    return PlacementSIETDB.getInstance().addCompany(companyDetails);
  }

  boolean scheduleInterview(InterviewSchedule interviewSchedule) {
    return PlacementSIETDB.getInstance().addInterviewSchedule(interviewSchedule);
  }

  boolean removeStudent(String email) {
    return PlacementSIETDB.getInstance().removeStudent(email);
  }
}
