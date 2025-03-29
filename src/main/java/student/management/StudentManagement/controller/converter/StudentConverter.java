package student.management.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentsCourses;
import student.management.StudentManagement.domain.StudentDetail;

/**
 * 受講生詳細を受講生や受講生コース情報、もしくはその逆の変換を行うコンバーター
 */
@Component
public class StudentConverter {

  /**
   * 受講生に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するため、ループを回して受講生詳細情報を組み立てる。
   *
   * @param students　受講生一覧
   * @param studentsCours　受講生コース情報のリスト
   * @return 受講生詳細情報のリスト
   */
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