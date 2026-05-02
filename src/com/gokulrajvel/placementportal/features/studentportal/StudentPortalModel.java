package com.gokulrajvel.placementportal.features.studentportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
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

    boolean updateSkills(StudentSignup studentSignup, String[] skills) {
        if (studentSignup == null || skills == null || skills.length == 0) {
            return false;
        }
        studentSignup.setSkill(skills);
        return true;
    }
}
