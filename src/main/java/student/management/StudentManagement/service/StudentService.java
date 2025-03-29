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
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録、更新処理を行います。
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
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return　受講生一覧(全件数)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.findByDeletedFalse();
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCourseList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * 受講生検索です。
   * IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param id　受講生ID
   * @return　受講生
   */
  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    return new StudentDetail(student, studentsCourses);
  }

  /**
   * 受講しえ情報とそのコース情報を登録します。
   * 受講生情報をデータベースに保存した後、その学生に紐づくコース情報を登録します。
   *
   * 登録されるコースの開始日は今日、終了日は1年後に設定されるようになっています。
   *
   * @param studentDetail　登録する受講生情報と、その受講生が受講するコース情報
   * @return　登録された受講生の情報
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
   * 受講生情報とその受講生が受講するコース情報を更新します。
   * 受講生情報をデータベースで更新した後、その受講生に紐づくコース情報も更新します。
   *
   * @param studentDetail　更新する受講生情報と、その受講生が受講するコース情報
   */
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      repository.updateStudentsCourses(studentsCourses);
    }
  }
}