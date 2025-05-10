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
 * 受講生情報テーブルと受講生コース情報テーブルと紐づくRepository。
 */
@Mapper
public interface StudentsRepository{

  /**
   * 受講生情報の全件検索を行う。
   *
   * @return　受講生情報(全件数)
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * idに紐づく受講生情報の検索を行う。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  /**
   * 受講生コース情報の全件検索を行う。
   *
   * @return　受講生コース情報(全件数)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourseList();

  /**
   * idに紐づく受講生コース情報の検索。
   *
   * @param studentId　受講生id
   * @return　受講生idに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

  @Insert("INSERT INTO students(name, furigana, nickname, email, area, age, gender, remark, isDeleted) " +
      "VALUES (#{name}, #{furigana}, #{nickname}, #{email}, #{area}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id, course_name, course_start_date, course_end_date) " +
      "VALUES (#{studentId}, #{courseName}, #{courseStartDate}, #{courseEndDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, email = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);

}
