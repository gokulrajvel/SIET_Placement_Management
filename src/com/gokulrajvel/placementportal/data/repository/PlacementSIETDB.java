package com.gokulrajvel.placementportal.data.repository;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlacementSIETDB {
    public static final Map<String, StudentSignup> STUDENTS = new LinkedHashMap<>();
    private static final Map<String, TeacherSignup> TEACHERS = new LinkedHashMap<>();
    private static final List<CompanyDetails> COMPANIES = new ArrayList<>();
    private static final List<InterviewSchedule> INTERVIEW_SCHEDULES = new ArrayList<>();

    private static PlacementSIETDB placementSIETDB;
    private int companyIdSequence = 1001;
    private int interviewIdSequence = 5001;

    private PlacementSIETDB(){
        seedDefaults();
    }

    public static PlacementSIETDB getInstance(){
        if(placementSIETDB == null){
            placementSIETDB = new PlacementSIETDB();
        }
        return placementSIETDB;
    }

    public synchronized boolean setSignupStudent(StudentSignup studentSignUp) {
        String email = normalize(studentSignUp.getEmail());
        if (STUDENTS.containsKey(email)) {
            return false;
        }
        studentSignUp.setEmail(email);
        STUDENTS.put(email, studentSignUp);
        return true;
    }

    public synchronized boolean setSignUpTeachers(TeacherSignup teacherSignUp) {
        String email = normalize(teacherSignUp.getEmail());
        if (TEACHERS.containsKey(email)) {
            return false;
        }
        teacherSignUp.setEmail(email);
        TEACHERS.put(email, teacherSignUp);
        return true;
    }

    public synchronized StudentSignup authenticateStudent(String email, String password) {
        StudentSignup student = STUDENTS.get(normalize(email));
        if (student == null || !student.getPassword().equals(password)) {
            return null;
        }
        return student;
    }

    public synchronized TeacherSignup authenticateTeacher(String email, String password) {
        TeacherSignup teacher = TEACHERS.get(normalize(email));
        if (teacher == null || !teacher.getPassword().equals(password)) {
            return null;
        }
        return teacher;
    }

    public synchronized List<StudentSignup> getStudentList() {
        return new ArrayList<>(STUDENTS.values());
    }

    public synchronized List<CompanyDetails> getCompanyList() {
        return new ArrayList<>(COMPANIES);
    }

    public synchronized CompanyDetails getCompanyById(int companyId) {
        for (CompanyDetails company : COMPANIES) {
            if (company.getCompany_id() == companyId) {
                return company;
            }
        }
        return null;
    }

    public synchronized boolean addCompany(CompanyDetails company) {
        if (company == null || company.getCompany_name() == null || company.getCompany_name().trim().isEmpty()) {
            return false;
        }
        company.setCompany_id(companyIdSequence++);
        COMPANIES.add(company);
        return true;
    }

    public synchronized boolean addInterviewSchedule(InterviewSchedule interviewSchedule) {
        if (interviewSchedule == null) {
            return false;
        }
        if (!STUDENTS.containsKey(normalize(interviewSchedule.getStudentEmail()))) {
            return false;
        }
        if (getCompanyById(interviewSchedule.getCompanyId()) == null) {
            return false;
        }
        interviewSchedule.setSchedule_id(interviewIdSequence++);
        INTERVIEW_SCHEDULES.add(interviewSchedule);
        return true;
    }

    public synchronized List<InterviewSchedule> getInterviewScheduleByStudent(String studentEmail) {
        List<InterviewSchedule> scheduleList = new ArrayList<>();
        String key = normalize(studentEmail);
        for (InterviewSchedule schedule : INTERVIEW_SCHEDULES) {
            if (normalize(schedule.getStudentEmail()).equals(key)) {
                scheduleList.add(schedule);
            }
        }
        return scheduleList;
    }

    private void seedDefaults() {
        if (!COMPANIES.isEmpty()) {
            return;
        }

        CompanyDetails c1 = new CompanyDetails();
        c1.setCompany_id(companyIdSequence++);
        c1.setCompany_name("Zoho");
        c1.setCompany_address("Chennai");
        c1.setCompany_email("careers@zoho.com");
        c1.setCompany_phone("04411112222");
        c1.setCompany_website("https://www.zoho.com");
        COMPANIES.add(c1);

        CompanyDetails c2 = new CompanyDetails();
        c2.setCompany_id(companyIdSequence++);
        c2.setCompany_name("TCS");
        c2.setCompany_address("Bengaluru");
        c2.setCompany_email("hiring@tcs.com");
        c2.setCompany_phone("08044445555");
        c2.setCompany_website("https://www.tcs.com");
        COMPANIES.add(c2);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}
