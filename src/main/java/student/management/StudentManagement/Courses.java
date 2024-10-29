package student.management.StudentManagement;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Courses {
  private String id;
  private String studentId;
  private String courseName;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;
}
