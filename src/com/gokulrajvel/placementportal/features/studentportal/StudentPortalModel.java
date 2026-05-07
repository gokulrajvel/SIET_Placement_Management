package com.gokulrajvel.placementportal.features.studentportal;

import com.gokulrajvel.placementportal.data.dto.*;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import java.util.List;

class StudentPortalModel {
  public StudentPortalModel(StudentPortalPresenter studentPortalPresenter) {
    // Presenter kept for MVP parity.
  }

  List<CompanyDetails> getCompanyList() {
    return PlacementSIETDB.getInstance().getCompanyList();
  }

  String getPlacementStatus(StudentSignup studentSignup) {
    return studentSignup.getPlacementStatus().name();
  }

  List<InterviewSchedule> getInterviewHistory(String studentEmail) {
    return PlacementSIETDB.getInstance().getInterviewScheduleByStudent(studentEmail);
  }

  boolean addSkills(StudentSignup studentSignup, String[] newSkills) {
    if (studentSignup == null || newSkills == null || newSkills.length == 0) {
      return false;
    }
    String[] currentSkills = studentSignup.getSkill();
    java.util.Set<String> skillSet = new java.util.LinkedHashSet<>();
    if (currentSkills != null) {
      for (String s : currentSkills) {
        if (!s.trim().isEmpty()) {
          skillSet.add(s.trim().toLowerCase());
        }
      }
    }
    for (String ns : newSkills) {
      if (!ns.trim().isEmpty()) {
        skillSet.add(ns.trim().toLowerCase());
      }
    }
    String[] updatedSkills = skillSet.toArray(new String[0]);
    studentSignup.setSkill(updatedSkills);
    return PlacementSIETDB.getInstance()
        .updateStudentSkills(studentSignup.getEmail(), updatedSkills);
  }
}
