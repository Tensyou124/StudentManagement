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
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるcontrollerです。
 */
@RestController
public class StudentController {

  private final StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return　受講生一覧(全件数)
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id　受講生ID
   * @return　受講生
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable String id){
    return service.searchStudent(id);
  }

  /**
   * 受講生情報とその受講生が受講するコース情報を登録します。
   * リクエストボディとして受け取った受講生情報をサービス層で処理し、登録後の情報を返します。
   *
   * @param studentDetail　登録する受講生情報と、その受講生が受講するコース情報(リクエストボディ)
   * @return　登録された受講生情報を含むレスポンス
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentdetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentdetail);
  }

  /**
   * 受講生情報とその受講生が受講するコース情報を更新します。
   * リクエストボディで受け取った受講生情報をサービス層で処理し、更新後に「更新処理が成功しました。」とメッセージが表示されます。
   *
   * @param studentDetail　更新する受講生情報と、その学生が受講するコース情報(リクエストボディ)
   * @return　更新処理の成功メッセージ
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}