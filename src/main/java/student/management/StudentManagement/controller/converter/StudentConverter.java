package student.management.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourse;
import student.management.StudentManagement.domain.StudentDetail;

/**
 * Serviceから取得したオブジェクトをControllerにとって必要な形に変換するクラス(コンバーター)。
 */
@Component
public class StudentConverter {

  /**
   * 受講生詳細に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するため、ループを回して受講生詳細情報を組み立てる。
   *
   * @param studentList　受講生詳細の一覧
   * @param studentCourseList　受講生コース情報のリスト
   * @return　受講生詳細情報のリスト
   */
  public List<StudentDetail> convertStudentDetails(List<Student> studentList, List<StudentCourse> studentCourseList) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    studentList.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourseList = studentCourseList.stream()
          .filter(studentsCourses -> student.getId() == studentsCourses.getStudentId())
          .collect(Collectors.toList());

      studentDetail.setStudentCourseList(convertStudentCourseList);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }

}
