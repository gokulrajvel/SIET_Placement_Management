package com.gokulrajvel.placementportal.features.teacherportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import util.ConsoleInput;
import util.ParseHelper;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TeacherPortalView {
    private final TeacherPortalPresenter teacherPortalPresenter;
    private final TeacherSignup teacher;
    private final Scanner scanner = ConsoleInput.getScanner();

    public TeacherPortalView(TeacherSignup teacher) {
        this.teacher = teacher;
        this.teacherPortalPresenter = new TeacherPortalPresenter(this);
    }

    public void init() {
        while (true) {
            System.out.println();
            System.out.printf("Teacher Portal - %s%n", teacher.getTeacherId());
            System.out.println("1. View Registered Students");
            System.out.println("2. View Company List");
            System.out.println("3. Add Company");
            System.out.println("4. Schedule Interview");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showStudents();
                    break;
                case "2":
                    showCompanyList();
                    break;
                case "3":
                    addCompany();
                    break;
                case "4":
                    scheduleInterview();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showStudents() {
        List<StudentSignup> students = teacherPortalPresenter.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        for (StudentSignup student : students) {
            System.out.printf("%s | %s | %s | %s | %s | %s%n",
                    student.getRegisterNo(), student.getName(), student.getEmail(),
                    student.getContactNo(), student.getBatch(), student.getPlacementStatus());
        }
    }

    private void showCompanyList() {
        List<CompanyDetails> companies = teacherPortalPresenter.getCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }
        for (CompanyDetails company : companies) {
            System.out.printf("%d | %s | %s | %s%n",
                    company.getCompany_id(), company.getCompany_name(),
                    company.getCompany_address(), company.getCompany_email());
        }
    }

    private void addCompany() {
        CompanyDetails company = new CompanyDetails();
        company.setCompany_name(readRequired("Company Name: "));
        company.setCompany_address(readRequired("Company Address: "));
        company.setCompany_email(readRequired("Company Email: "));
        company.setCompany_phone(readRequired("Company Phone: "));
        company.setCompany_website(readRequired("Company Website: "));

        if (teacherPortalPresenter.addCompany(company)) {
            System.out.println("Company added successfully.");
            return;
        }
        System.out.println("Unable to add company.");
    }

    private void scheduleInterview() {
        showCompanyList();
        InterviewSchedule interviewSchedule = new InterviewSchedule();
        interviewSchedule.setCompanyId(readCompanyId());
        interviewSchedule.setStudentEmail(readRequired("Student Email: "));
        interviewSchedule.setTeacherId(teacher.getTeacherId());
        interviewSchedule.setSchedule_date(readScheduleDate());
        interviewSchedule.setSchedule_time(readRequired("Schedule Time (e.g. 10:00 AM): "));
        interviewSchedule.setLocationOrLink(readRequired("Location/Meeting Link: "));
        interviewSchedule.setRemarks(readRequired("Remarks: "));

        if (teacherPortalPresenter.scheduleInterview(interviewSchedule)) {
            System.out.println("Interview scheduled successfully.");
            return;
        }
        System.out.println("Unable to schedule interview. Check student email and company id.");
    }

    private int readCompanyId() {
        while (true) {
            System.out.print("Company Id: ");
            String value = scanner.nextLine().trim();
            try {
                int companyId = Integer.parseInt(value);
                if (companyId > 0) {
                    return companyId;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid company id.");
        }
    }

    private Date readScheduleDate() {
        while (true) {
            System.out.printf("Schedule Date (%s): ", ParseHelper.getDobPattern());
            String value = scanner.nextLine().trim();
            Date date = ParseHelper.parseDate(value);
            if (date != null) {
                return date;
            }
            System.out.println("Invalid date.");
        }
    }

    private String readRequired(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field is required.");
        }
    }
}
