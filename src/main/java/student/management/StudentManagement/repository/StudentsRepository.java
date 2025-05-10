package student.management.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourse;

/**
 * 受講生詳細テーブルと受講生コース情報テーブルと紐づくRepository。
 */
@Mapper
public interface StudentsRepository{

  /**
   * 受講生詳細の全件検索を行う。
   *
   * @return　受講生詳細(全件数)
   */
  List<Student> search();

  /**
   * idに紐づく受講生詳細の検索を行う。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生詳細
   */
  Student searchStudent(String id);

  /**
   * 受講生コース情報の全件検索を行う。
   *
   * @return　受講生コース情報(全件数)
   */
  List<StudentCourse> searchStudentCourseList();

  /**
   * idに紐づく受講生コース情報の検索。
   *
   * @param studentId　受講生id
   * @return　受講生idに紐づく受講生コース情報
   */
  List<StudentCourse> searchStudentCourse(String studentId);

  /**
   * 受講生を新規登録する。
   * IDは自動採番を行う。
   *
   * @param student　受講生
   */
  void registerStudent(Student student);

  /**
   * 受講生コース情報を新規登録する。
   * IDは自動採番を行う。
   *
   * @param studentCourse　受講生コース情報
   */
  void registerStudentCourse(StudentCourse studentCourse);

  /**
   * 受講生詳細の更新を行う。
   *
   * @param student　受講生
   */
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を変更する。
   *
   * @param studentCourse　受講生コース情報
   */
  void updateStudentCourse(StudentCourse studentCourse);

}
