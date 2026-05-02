package com.gokulrajvel.placementportal.features.teacherportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;

import java.util.List;

class TeacherPortalPresenter {
    private final TeacherPortalModel teacherPortalModel;

    public TeacherPortalPresenter(TeacherPortalView teacherPortalView) {
        this.teacherPortalModel = new TeacherPortalModel(this);
    }

    List<StudentSignup> getStudents() {
        return teacherPortalModel.getStudents();
    }

    List<CompanyDetails> getCompanies() {
        return teacherPortalModel.getCompanies();
    }

    boolean addCompany(CompanyDetails companyDetails) {
        return teacherPortalModel.addCompany(companyDetails);
    }

    boolean scheduleInterview(InterviewSchedule interviewSchedule) {
        return teacherPortalModel.scheduleInterview(interviewSchedule);
    }
}
