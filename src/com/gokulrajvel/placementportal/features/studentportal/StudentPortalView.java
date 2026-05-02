package com.gokulrajvel.placementportal.features.studentportal;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;

import java.util.List;

import static util.ConsoleInput.scanner;

public class StudentPortalView {
    private final StudentPortalPresenter studentPortalPresenter;
    private final StudentSignup student;

    public StudentPortalView(StudentSignup studentSignup) {
        this.student = studentSignup;
        this.studentPortalPresenter = new StudentPortalPresenter(this);
    }

    public void init() {
        while (true) {
            System.out.println();
            System.out.printf("Student Portal - %s%n", student.getName());
            System.out.println("1. View Company List");
            System.out.println("2. View Placement Status");
            System.out.println("3. View Interview History");
            System.out.println("4. Update Skills");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showCompanyList();
                    break;
                case "2":
                    showPlacementStatus();
                    break;
                case "3":
                    showInterviewHistory();
                    break;
                case "4":
                    updateSkills();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showCompanyList() {
        List<CompanyDetails> companyList = studentPortalPresenter.getCompanyList();
        if (companyList.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }
        System.out.println();
        System.out.println("Available Companies");
        for (CompanyDetails company : companyList) {
            System.out.printf("%d. %s | %s | %s%n",
                    company.getCompany_id(), company.getCompany_name(),
                    company.getCompany_address(), company.getCompany_email());
        }
    }

    private void showPlacementStatus() {
        System.out.printf("Current Placement Status: %s%n", studentPortalPresenter.getPlacementStatus(student));
    }

    private void showInterviewHistory() {
        List<InterviewSchedule> schedules = studentPortalPresenter.getInterviewHistory(student.getEmail());
        if (schedules.isEmpty()) {
            System.out.println("No interviews scheduled.");
            return;
        }
        System.out.println();
        System.out.println("Interview History");
        for (InterviewSchedule schedule : schedules) {
            CompanyDetails company = PlacementSIETDB.getInstance().getCompanyById(schedule.getCompanyId());
            String companyName = company == null ? "Unknown" : company.getCompany_name();
            System.out.printf("Schedule ID: %d%n", schedule.getSchedule_id());
            System.out.printf("Company: %s%n", companyName);
            System.out.printf("Date: %s%n", schedule.getFormattedDate());
            System.out.printf("Time: %s%n", schedule.getSchedule_time());
            System.out.printf("Location/Link: %s%n", schedule.getLocationOrLink());
            System.out.printf("Status: %s%n", schedule.getInterviewStatus());
            System.out.printf("Remarks: %s%n", schedule.getRemarks());
            System.out.println("------------------------------------");
        }
    }

    private void updateSkills() {
        String[] skills = readSkills();
        if (studentPortalPresenter.updateSkills(student, skills)) {
            System.out.println("Skills updated successfully.");
            System.out.printf("Current Skills: %s%n", String.join(", ", skills));
            return;
        }
        System.out.println("Unable to update skills.");
    }

    private String[] readSkills() {
        while (true) {
            System.out.print("Enter skills (comma separated): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skills cannot be empty.");
                continue;
            }

            String[] raw = input.split(",");
            int validCount = 0;
            for (String value : raw) {
                if (!value.trim().isEmpty()) {
                    validCount++;
                }
            }
            if (validCount == 0) {
                System.out.println("Provide at least one valid skill.");
                continue;
            }

            String[] skills = new String[validCount];
            int index = 0;
            for (String value : raw) {
                String skill = value.trim();
                if (!skill.isEmpty()) {
                    skills[index++] = skill;
                }
            }
            return skills;
        }
    }
}
