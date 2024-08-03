package student.management.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private Map<String, Integer> students = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	public StudentManagementApplication() {
		students.put("箭内", 50);
		students.put("椿", 30);
		students.put("江口", 59);
		students.put("塩田", 57);
		students.put("柳田", 53);
		students.put("大内", 62);
	}

	@GetMapping("/studentInfo")
	public Map<String, Integer> getStudentInfo() {
		return students;
	}

	@GetMapping("/studentInfoByName")
	public String getStudentInfoByName(@RequestParam String name) {
		Integer age = students.get(name);
		if (age != null) {
			return name + "さんは" + age + "歳です";
		} else {
			return name + "さんは見つかりませんでした。";
		}
	}

	@PostMapping("/addStudent")
	public String addStudent(@RequestParam String name, @RequestParam Integer age) {
		students.put(name, age);
		return "追加しました。";
	}

	@PostMapping("/updateStudent")
	public String updateStudent(@RequestParam String name, @RequestParam Integer age) {
		if (students.containsKey(name)) {
			students.put(name, age);
			return "更新しました。";
		} else {
			return name + "さんは見つかりませんでした。";
		}
	}
}
