package student.management.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students WHERE age >= 30 and age < 40")
  List<Student> searchStudents();

  @Select("SELECT * FROM students_courses WHERE id = 1")
  List<StudentCourses> searchCourses();
}





