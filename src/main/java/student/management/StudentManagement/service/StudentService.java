package student.management.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentsCourses;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.repository.StudentsRepository;

/**
 * 受講生情報を取り扱うサービス。
 * 検索、登録、更新などを行う。
 */
@Service
public class StudentService {

  private final StudentsRepository repository;
  private final StudentConverter converter;

  @Autowired
  public StudentService(StudentsRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生情報の一覧検索を行う。
   * 全件検索のため、条件指定は行わない。
   *
   * @return　受講生情報(全件数)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCourseList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * 受講生情報を検索する。
   * idに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定する。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生情報
   */
  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    return new StudentDetail(student, studentsCourses);
  }

  /**
   * 新たに受講生情報を追加
   *
   * @param studentDetail　受講生情報
   * @return　追加した受講生情報
   */

  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      studentsCourses.setCourseStartDate(LocalDate.now());
      studentsCourses.setCourseEndDate(LocalDate.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourses);
    }
    return studentDetail;
  }

  /**
   * 既存の受講生情報を更新する。
   *
   * @param studentDetail　受講生情報
   */
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      repository.updateStudentsCourses(studentsCourses);
    }
  }
}