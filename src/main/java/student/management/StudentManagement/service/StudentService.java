package student.management.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourse;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.repository.StudentsRepository;

/**
 * 受講生詳細を取り扱うサービス。
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
   * 受講生詳細の一覧検索を行う。
   * 全件検索のため、条件指定は行わない。
   *
   * @return　受講生詳細(全件数)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生詳細を検索する。
   * idに紐づく受講生詳細を取得した後、その受講生に紐づく受講生コース情報を取得して設定する。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生詳細
   */
  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentsCours = repository.searchStudentCourse(student.getId());
    return new StudentDetail(student, studentsCours);
  }

  /**
   * 新たに受講生詳細を追加。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値と日付情報(コース開始日、コース終了日)を設定する。
   *
   * @param studentDetail　受講生詳細
   * @return　追加した受講生詳細
   */

  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourses -> {
      initStudentsCourse(studentsCourses, student);
      repository.registerStudentCourse(studentsCourses);
    });
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourse　受講生コース情報
   * @param student　受講生
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    LocalDate now = LocalDate.now();
    studentCourse.setStudentId(student.getId());
    studentCourse.setCourseStartDate(now);
    studentCourse.setCourseEndDate(now.plusYears(1));
  }

  /**
   * 既存の受講生詳細を更新する。
   * 受講生と受講生コース情報をそれぞれ更新する。
   *
   * @param studentDetail　受講生詳細
   */
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList().forEach(repository::updateStudentCourse);
  }
}