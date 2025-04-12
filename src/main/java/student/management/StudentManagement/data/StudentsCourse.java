package student.management.StudentManagement.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourse {
  private String id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartDate;
  private LocalDate courseEndDate;
}