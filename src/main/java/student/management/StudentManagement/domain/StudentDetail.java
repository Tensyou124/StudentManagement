package student.management.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {
  @Setter
  @Getter
  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String gender;
  private String remark;
  private Student student;
  private List<StudentsCourses> studentsCourses;
}