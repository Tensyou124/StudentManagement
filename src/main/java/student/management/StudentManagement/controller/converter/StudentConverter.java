package student.management.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentsCourses;
import student.management.StudentManagement.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students, List<StudentsCourses> studentsCours) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentsCours = studentsCours.stream()
          .filter(studentsCourses -> student.getId() == studentsCourses.getStudentId())
          .collect(Collectors.toList());

      studentDetail.setStudentsCourses(convertStudentsCours);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
