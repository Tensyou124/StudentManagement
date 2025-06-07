package student.management.StudentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.exception.TestException;
import student.management.StudentManagement.service.StudentService;

//Operationの動作確認は「http://localhost:8080/swagger-ui/index.html」から。

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるController
 */
@Validated
@RestController
public class StudentController {

  private final StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細を取得する。
   * 全件検索のため、条件指定は行わない。
   *
   * @return　受講生詳細(全件数)
   */
  @Operation(summary = "一覧検索" , description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細を取得する。
   * idに紐づく任意の受講生詳細を取得する。
   *
   * @param id　受講生id
   * @return　idに紐づく受講生詳細
   */
  @Operation(summary = "受講生の検索" , description = "受講生IDを元に受講生を検索します。")
  @GetMapping("/students/{id}")
  public ResponseEntity<StudentDetail> getStudentDetail(@PathVariable @Size(min=1,max=4) String id) {
    StudentDetail studentDetail = service.searchStudent(id);
    return ResponseEntity.ok(studentDetail);
  }

  /**
   * 受講生詳細を新たに登録する。
   *
   * @param studentDetail　受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生登録" , description = "受講生を新規登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行う。
   * キャンセルフラグの更新もここで行う。(論理削除)
   * 処理が成功すると「更新処理が完了しました。」と表示される。
   *
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @Operation(summary = "受講生情報の更新" , description = "受講生情報を更新します。")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が完了しました。");
  }

  /**
   * テスト用の例外を発生させるAPI。
   *
   * @return なし（常に例外をスローする）
   */
  @Operation(summary = "テスト用例外" , description = "テスト用に例外を発生させる専用APIです。")
  @GetMapping("/testException")
  public void throwTestException() throws TestException {
    throw new TestException("これはテスト用の例外です。");
  }
}
