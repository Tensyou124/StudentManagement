package student.management.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.management.StudentManagement.data.Courses;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> getStudentList() {
    return repository.searchStudents();
  }

  public List<Courses> getCourseList() {
    return repository.searchCourses();
  }
}
