package student.management.StudentManagement.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {
  private String id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartDate;
  private LocalDate courseEndDate;
}