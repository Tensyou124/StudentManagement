package student.management.StudentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {



  @Select("SELECT * FROM student WHERE name = #{name}")
  Student selectByName(String name);

  @Insert("INSERT student values(#{name}, #{age})")
  void addStudent(String name, int age);

  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudentAge(String name, int age);

  @Delete("DELETE FROM student WHERE name=#{name}")
  void deleteStudnet(String name);


  @Select("SELECT * FROM student")
  List<Student> allStudents();
}





