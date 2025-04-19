package student.management.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるController
 */
@RestController
public class StudentController {

  private final StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生情報を取得する。
   * 全件検索のため、条件指定は行わない。
   *
   * @return　受講生情報(全件数)
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生情報を取得する。
   * idに紐づく任意の受講生情報を取得する。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生情報
   */
  @GetMapping("/students/{id}")
  public ResponseEntity<StudentDetail> getStudentDetail(@PathVariable String id) {
    StudentDetail studentDetail = service.searchStudent(id);
    return ResponseEntity.ok(studentDetail);
  }

  /**
   * 受講生情報を新たに登録する。
   *
   * @param studentDetail
   * @return
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 既存の受講生情報を更新する。
   *
   * @param studentDetail
   * @return
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が完了しました。");
  }
}
