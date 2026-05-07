package com.gokulrajvel.placementportal.data.repository;

import com.gokulrajvel.placementportal.data.dto.CompanyDetails;
import com.gokulrajvel.placementportal.data.dto.InterviewSchedule;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlacementSIETDB {

  private static PlacementSIETDB placementSIETDB;
  private static final Connection conn = PlacementJDBCConnection.getConnection();

  private PlacementSIETDB() {
    //        seedDefaults();
  }

  public static PlacementSIETDB getInstance() {
    if (placementSIETDB == null) {
      placementSIETDB = new PlacementSIETDB();
    }
    return placementSIETDB;
  }

  public static void createTable() {
    String studentSQL =
        """
                CREATE TABLE IF NOT EXISTS Student(
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL UNIQUE,
                    DOB Date NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    contactNo VARCHAR(20) NOT NULL,
                    skill VARCHAR(100) NOT NULL,
                    batch VARCHAR(20) NOT NULL,
                    placementStatus VARCHAR(20) NOT NULL DEFAULT 'NOT_PLACED'
                )
                """;
    try {
      PreparedStatement var = conn.prepareStatement(studentSQL);
      var.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String teacherSQL =
        """
                CREATE TABLE IF NOT EXISTS TEACHER
                (
                teacherID INT PRIMARY KEY,
                Name VARCHAR(100) NOT NULL UNIQUE,
                Email VARCHAR(100) NOT NULL UNIQUE,
                password VARCHAR(100) NOT NULL,
                DOB DATE NOT NULL,
                PhoneNO VARCHAR(11) NOT NULL
                )
                """;
    try {
      PreparedStatement var = conn.prepareStatement(teacherSQL);
      var.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String companyDetails =
        """
                CREATE TABLE IF NOT EXISTS CompanyDetails
                (
                company_id INT PRIMARY KEY,
                company_name VARCHAR(100) NOT NULL UNIQUE,
                company_address VARCHAR(100) NOT NULL,
                company_email VARCHAR(100) NOT NULL UNIQUE,
                company_phone VARCHAR(11) NOT NULL,
                company_website VARCHAR(100) NOT NULL
                )
                """;
    try {
      PreparedStatement var = conn.prepareStatement(companyDetails);
      var.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String interViewSchedules =
        """
                CREATE TABLE IF NOT EXISTS INTERVIEW_SCHEDULES (
                   schedule_id INT PRIMARY KEY,
                   company_id INT,
                   schedule_date DATE NOT NULL,
                   schedule_time VARCHAR(10) NOT NULL,
                   locationOrLink VARCHAR(255) NOT NULL,
                   studentEmail VARCHAR(100),
                   teacherId INT,
                   interviewStatus VARCHAR(30),
                   remark VARCHAR(255),
                   FOREIGN KEY (company_id) REFERENCES CompanyDetails(company_id),
                   FOREIGN KEY (studentEmail) REFERENCES Student(email),
                   FOREIGN KEY (teacherId) REFERENCES TEACHER(teacherID)
                 )
                """;
    try {
      PreparedStatement var = conn.prepareStatement(interViewSchedules);
      var.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized boolean setSignUpStudent(StudentSignup studentSignUp) {
    String selectQuery = "SELECT * FROM Student WHERE email = ?";
    try (PreparedStatement var = conn.prepareStatement(selectQuery)) {
      var.setString(1, normalize(studentSignUp.getEmail()));
      try (ResultSet resultSet = var.executeQuery()) {
        if (resultSet.next()) {
          return false;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String insertQuery =
        """
                INSERT INTO Student(
                    id,name,DOB,email,password,contactNo,skill,batch, placementStatus) VALUES (?,?,?,?,?,?,?,?,?)
                """;
    try (PreparedStatement var = conn.prepareStatement(insertQuery)) {
      var.setInt(1, Integer.parseInt(studentSignUp.getRegisterNo()));
      var.setString(2, studentSignUp.getName());
      var.setDate(3, java.sql.Date.valueOf(studentSignUp.getBirthDate()));
      var.setString(4, studentSignUp.getEmail());
      var.setString(5, studentSignUp.getPassword());
      var.setString(6, studentSignUp.getContactNo());
      var.setString(7, Arrays.toString(studentSignUp.getSkill()));
      var.setString(8, studentSignUp.getBatch());
      var.setString(9, studentSignUp.getPlacementStatus().toString());
      return var.executeUpdate() > 0;
    } catch (SQLException | IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized boolean setSignUpTeachers(TeacherSignup teacherSignUp) {
    String selectQuery = "SELECT * FROM TEACHER WHERE email = ?";
    try (PreparedStatement var = conn.prepareStatement(selectQuery)) {
      var.setString(1, normalize(teacherSignUp.getEmail()));
      try (ResultSet resultSet = var.executeQuery()) {
        if (resultSet.next()) {
          return false;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String insertQuery =
        """
                INSERT INTO TEACHER(teacherID,Name,Email,password,DOB,PhoneNO) VALUES (?,?,?,?,?,?)
                """;
    try (PreparedStatement var = conn.prepareStatement(insertQuery)) {
      var.setInt(1, Integer.parseInt(teacherSignUp.getTeacherId()));
      var.setString(2, teacherSignUp.getName());
      var.setString(3, teacherSignUp.getEmail());
      var.setString(4, teacherSignUp.getPassword());
      var.setDate(5, java.sql.Date.valueOf(teacherSignUp.getBirthDate()));
      var.setString(6, teacherSignUp.getPhoneNo());
      return var.executeUpdate() > 0;
    } catch (SQLException | IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  public StudentSignup authenticateStudent(String email, String password) {
    String query = "SELECT * FROM Student WHERE email = ? AND password = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setString(1, normalize(email));
      statement.setString(2, password);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          StudentSignup student = new StudentSignup();
          student.setRegisterNo(String.valueOf(rs.getInt("id")));
          student.setName(rs.getString("name"));
          student.setBirthDate(rs.getDate("DOB").toString());
          student.setEmail(rs.getString("email"));
          student.setPassword(rs.getString("password"));
          student.setContactNo(rs.getString("contactNo"));
          String skillStr = rs.getString("skill");
          if (skillStr != null && skillStr.length() > 2) {
            student.setSkill(skillStr.substring(1, skillStr.length() - 1).split(",\\s*"));
          } else {
            student.setSkill(new String[0]);
          }
          student.setBatch(rs.getString("batch"));
          student.setPlacementStatus(
              StudentSignup.PlacementStatus.valueOf(rs.getString("placementStatus")));
          return student;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public TeacherSignup authenticateTeacher(String email, String password) {
    String query = "SELECT * FROM TEACHER WHERE Email = ? AND password = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setString(1, normalize(email));
      statement.setString(2, password);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          TeacherSignup teacher = new TeacherSignup();
          teacher.setTeacherId(String.valueOf(rs.getInt("teacherID")));
          teacher.setEmail(rs.getString("Email"));
          teacher.setPassword(rs.getString("password"));
          teacher.setBirthDate(rs.getDate("DOB").toString());
          teacher.setPhoneNo(rs.getString("PhoneNO"));
          return teacher;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<CompanyDetails> getCompanyList() {
    List<CompanyDetails> companies = new ArrayList<>();
    String query = "SELECT * FROM CompanyDetails";
    try (PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery()) {
      while (rs.next()) {
        CompanyDetails company = new CompanyDetails();
        company.setCompanyId(rs.getInt("company_id"));
        company.setCompanyName(rs.getString("company_name"));
        company.setCompanyAddress(rs.getString("company_address"));
        company.setCompanyEmail(rs.getString("company_email"));
        company.setCompanyPhone(rs.getString("company_phone"));
        company.setCompanyWebsite(rs.getString("company_website"));
        companies.add(company);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return companies;
  }

  public CompanyDetails getCompanyById(int companyId) {
    String query = "SELECT * FROM CompanyDetails WHERE company_id = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setInt(1, companyId);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          CompanyDetails company = new CompanyDetails();
          company.setCompanyId(rs.getInt("company_id"));
          company.setCompanyName(rs.getString("company_name"));
          company.setCompanyAddress(rs.getString("company_address"));
          company.setCompanyEmail(rs.getString("company_email"));
          company.setCompanyPhone(rs.getString("company_phone"));
          company.setCompanyWebsite(rs.getString("company_website"));
          return company;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<StudentSignup> getStudentList() {
    List<StudentSignup> students = new ArrayList<>();
    String query = "SELECT * FROM Student";
    try (PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery()) {
      while (rs.next()) {
        StudentSignup student = new StudentSignup();
        student.setRegisterNo(String.valueOf(rs.getInt("id")));
        student.setName(rs.getString("name"));
        student.setBirthDate(rs.getDate("DOB").toString());
        student.setEmail(rs.getString("email"));
        student.setPassword(rs.getString("password"));
        student.setContactNo(rs.getString("contactNo"));
        String skillStr = rs.getString("skill");
        if (skillStr != null && skillStr.length() > 2) {
          student.setSkill(skillStr.substring(1, skillStr.length() - 1).split(",\\s*"));
        } else {
          student.setSkill(new String[0]);
        }
        student.setBatch(rs.getString("batch"));
        student.setPlacementStatus(
            StudentSignup.PlacementStatus.valueOf(rs.getString("placementStatus")));
        students.add(student);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  public List<InterviewSchedule> getInterviewScheduleByStudent(String studentEmail) {
    List<InterviewSchedule> schedules = new ArrayList<>();
    String query = "SELECT * FROM INTERVIEW_SCHEDULES WHERE studentEmail = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setString(1, normalize(studentEmail));
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          InterviewSchedule schedule = new InterviewSchedule();
          schedule.setScheduleId(rs.getInt("schedule_id"));
          schedule.setCompanyId(rs.getInt("company_id"));
          schedule.setScheduleDate(rs.getDate("schedule_date"));
          schedule.setScheduleTime(rs.getString("schedule_time"));
          schedule.setLocationOrLink(rs.getString("locationOrLink"));
          schedule.setStudentEmail(rs.getString("studentEmail"));
          schedule.setTeacherId(String.valueOf(rs.getInt("teacherId")));
          String statusStr = rs.getString("interviewStatus");
          if (statusStr != null) {
            schedule.setInterviewStatus(InterviewSchedule.InterviewStatus.valueOf(statusStr));
          }
          schedule.setRemarks(rs.getString("remark"));
          schedules.add(schedule);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return schedules;
  }

  public synchronized boolean addCompany(CompanyDetails companyDetails) {
    String query =
        "INSERT INTO CompanyDetails(company_id, company_name, company_address, company_email,"
            + " company_phone, company_website) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setInt(1, companyDetails.getCompanyId());
      statement.setString(2, companyDetails.getCompanyName());
      statement.setString(3, companyDetails.getCompanyAddress());
      statement.setString(4, companyDetails.getCompanyEmail());
      statement.setString(5, companyDetails.getCompanyPhone());
      statement.setString(6, companyDetails.getCompanyWebsite());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public synchronized boolean addInterviewSchedule(InterviewSchedule interviewSchedule) {
    String query =
        "INSERT INTO INTERVIEW_SCHEDULES(schedule_id, company_id, schedule_date, schedule_time,"
            + " locationOrLink, studentEmail, teacherId, interviewStatus, remark) VALUES (?, ?, ?,"
            + " ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setInt(1, interviewSchedule.getScheduleId());
      statement.setInt(2, interviewSchedule.getCompanyId());
      statement.setDate(3, new java.sql.Date(interviewSchedule.getScheduleDate().getTime()));
      statement.setString(4, interviewSchedule.getScheduleTime());
      statement.setString(5, interviewSchedule.getLocationOrLink());
      statement.setString(6, interviewSchedule.getStudentEmail());
      statement.setInt(7, Integer.parseInt(interviewSchedule.getTeacherId()));
      statement.setString(8, interviewSchedule.getInterviewStatus().name());
      statement.setString(9, interviewSchedule.getRemarks());
      return statement.executeUpdate() > 0;
    } catch (SQLException | NumberFormatException e) {
      e.printStackTrace();
    }
    return false;
  }

  public synchronized boolean updateStudentSkills(String email, String[] skills) {
    String query = "UPDATE Student SET skill = ? WHERE email = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      statement.setString(1, Arrays.toString(skills));
      statement.setString(2, normalize(email));
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public synchronized boolean removeStudent(String email) {
    String deleteInterviews = "DELETE FROM INTERVIEW_SCHEDULES WHERE studentEmail = ?";
    String deleteStudent = "DELETE FROM Student WHERE email = ?";
    try {
      conn.setAutoCommit(false);
      try (PreparedStatement stat1 = conn.prepareStatement(deleteInterviews);
          PreparedStatement stat2 = conn.prepareStatement(deleteStudent)) {
        stat1.setString(1, normalize(email));
        stat1.executeUpdate();

        stat2.setString(1, normalize(email));
        int deleted = stat2.executeUpdate();

        conn.commit();
        return deleted > 0;
      } catch (SQLException e) {
        conn.rollback();
        e.printStackTrace();
      } finally {
        conn.setAutoCommit(true);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static String normalize(String value) {
    return value == null ? "" : value.trim().toLowerCase();
  }
}
