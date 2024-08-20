package student.management.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	@Autowired
	private StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/student")
	public String getStudent(@RequestParam String name) {
		Student student = repository.selectByName(name);
		return student.getName() + " " + student.getAge() + "歳";
	}

	@PostMapping("/Student")
	public void addStudent(String name, int age) {
		repository.addStudent(name, age);
	}

	@PatchMapping("/Student")
	public void updateStudentName(String name, int age){
		repository.updateStudent(name, age);
	}

	@DeleteMapping("/Student")
	public void deleteStudent(String name){
		repository.deleteStudnet(name);
	}
}