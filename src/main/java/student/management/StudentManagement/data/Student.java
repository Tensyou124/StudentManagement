package student.management.StudentManagement.data;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {
  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
  private List<StudentsCourses> studentsCourses;
}

