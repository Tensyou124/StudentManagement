package student.management.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentsCourses;

/**
 * 受講生テーブルと受講生情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentsRepository{

  /**
   * 受講生の全件検索を行います。
   *
   * @return　受講生一覧(全件数)
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * 受講生の検索を行います。
   *
   * @param id　受講生ID
   * @return　受講生
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  @Select("SELECT * FROM students WHERE isDeleted = false")
  List<Student> findByDeletedFalse();

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return　受講生のコース情報(全件数)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourseList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentId　受講生ID
   * @return　受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

  /**
   * 受講生情報をデータベースに登録します。
   * 受講生の名前、フリガナ、ニックネーム、メールアドレス、地域、年齢、性別、備考を登録し、
   * 'isDeleted'フラグはデフォルトで'false'に設定されます。
   * 登録後、生成された受講生のIDを'student'オブジェクトに設定します。
   *
   * @param student　登録する受講生情報
   */
  @Insert("INSERT INTO students(name, furigana, nickname, email, area, age, gender, remark, isDeleted) " +
      "VALUES (#{name}, #{furigana}, #{nickname}, #{email}, #{area}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  /**
   * 受講生のコース情報をデータベースに登録します。
   * 指定された受講生IDに紐づくコース名、コース開始日、コース終了日を登録します。
   * 登録後、生成されたコースIDを'studentsCourses'オブジェクトに設定します。
   *
   * @param studentsCourses　登録する受講生のコース情報
   */
  @Insert("INSERT INTO students_courses(student_id, course_name, course_start_date, course_end_date) " +
      "VALUES (#{studentId}, #{courseName}, #{courseStartDate}, #{courseEndDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  /**
   * 指定された受講生IDに対応する受講生情報を更新します。
   * 名前、フリガナ、ニックネーム、メールアドレス、地域、年齢、性別、備考、削除フラグの更新を行います。
   *
   * @param student　更新する学生情報(ＩＤを含む)
   */
  @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, email = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * 指定されたコースIＤｎい対応するコース名を更新します。
   *
   * @param studentsCourses　更新するコース情報(ＩＤと新しいコース名を含む)
   */
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);

}