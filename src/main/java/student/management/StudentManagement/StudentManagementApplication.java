package student.management.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.repository.StudentsRepository;

@SpringBootApplication
@RestController
public class StudentManagementApplication {



	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
}
