package com.gokulrajvel.placementportal.features.studentportal;

import static util.ConsoleInput.scanner;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import java.util.List;

public class StudentPortalView {
  private final StudentPortalPresenter studentPortalPresenter;
  private final StudentSignup student;

  public StudentPortalView(StudentSignup studentSignup) {
    this.student = studentSignup;
    this.studentPortalPresenter = new StudentPortalPresenter(this);
  }

  public void init() {
    while (true) {
      String title = String.format("Student Portal - %s", student.getName());
      util.UIUtils.printMenu(
          title,
          new String[] {
            "1. View Company List",
            "2. View Placement Status",
            "3. View Interview History",
            "4. Add Skills",
            "5. My Profile",
            "6. Logout"
          });
      util.UIUtils.printCenterPrompt("Choose an option: ");
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
          addSkills();
          break;
        case "5":
          showMyProfile();
          break;
        case "6":
          return;
        default:
          util.UIUtils.printCenter("Invalid option. Please try again.");
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
      System.out.printf(
          "%d. %s | %s | %s%n",
          company.getCompany_id(),
          company.getCompany_name(),
          company.getCompany_address(),
          company.getCompany_email());
    }
  }

  private void showPlacementStatus() {
    System.out.printf(
        "Current Placement Status: %s%n", studentPortalPresenter.getPlacementStatus(student));
  }

  private void showInterviewHistory() {
    List<InterviewSchedule> schedules =
        studentPortalPresenter.getInterviewHistory(student.getEmail());
    if (schedules.isEmpty()) {
      System.out.println("No interviews scheduled.");
      return;
    }
    System.out.println();
    System.out.println("Interview History");
    for (InterviewSchedule schedule : schedules) {
      CompanyDetails company =
          PlacementSIETDB.getInstance().getCompanyById(schedule.getCompanyId());
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

  private void addSkills() {
    String[] skills = readSkills();
    if (studentPortalPresenter.addSkills(student, skills)) {
      System.out.println("Skills added successfully.");
      System.out.printf("Current Skills: %s%n", String.join(", ", student.getSkill()));
      return;
    }
    System.out.println("Unable to add skills.");
  }

  private void showMyProfile() {
    System.out.println();
    System.out.println("My Profile");
    System.out.printf("Register No: %s%n", student.getRegisterNo());
    System.out.printf("Name: %s%n", student.getName());
    System.out.printf("DOB: %s%n", student.getBirthDate());
    System.out.printf("Email: %s%n", student.getEmail());
    System.out.printf("Contact No: %s%n", student.getContactNo());
    String[] skills = student.getSkill();
    System.out.printf(
        "Skills: %s%n", (skills != null && skills.length > 0) ? String.join(", ", skills) : "None");
    System.out.printf("Batch: %s%n", student.getBatch());
    System.out.printf("Placement Status: %s%n", student.getPlacementStatus());
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
