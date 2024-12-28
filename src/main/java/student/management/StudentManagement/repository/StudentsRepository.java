package student.management.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;


@Mapper
public interface StudentsRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudents();

  @Select("SELECT * FROM students_courses WHERE id = 1")
  List<StudentCourses> searchCourses();

  @Insert("INSERT INTO students(name,furigana,nickname,email,area,age,gender,remark,isDeleted)"
      + "VALUES (#{name},#{furigana},#{nickname},#{email},#{area},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);
}




