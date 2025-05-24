package student.management.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
public class StudentCourse {
  private String id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartDate;
  private LocalDate courseEndDate;
}
