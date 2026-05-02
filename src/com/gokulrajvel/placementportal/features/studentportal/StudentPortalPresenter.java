package com.gokulrajvel.placementportal.features.studentportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;

import java.util.List;

class StudentPortalPresenter {
    private final StudentPortalView studentPortalView;
    private final StudentPortalModel studentPortalModel;

    public StudentPortalPresenter(StudentPortalView studentPortalView) {
        this.studentPortalView = studentPortalView;
        this.studentPortalModel=new StudentPortalModel(this);
    }

    List<CompanyDetails> getCompanyList() {
        return studentPortalModel.getCompanyList();
    }

    String getPlacementStatus(StudentSignup studentSignup) {
        return studentPortalModel.getPlacementStatus(studentSignup);
    }

    List<InterviewSchedule> getInterviewHistory(String studentEmail) {
        return studentPortalModel.getInterviewHistory(studentEmail);
    }

    boolean updateSkills(StudentSignup studentSignup, String[] skills) {
        return studentPortalModel.updateSkills(studentSignup, skills);
    }
}
