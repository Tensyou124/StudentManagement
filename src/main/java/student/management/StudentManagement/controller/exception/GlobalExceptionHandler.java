package student.management.StudentManagement.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import student.management.StudentManagement.exception.TestException;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 独自のTestExceptionを処理する。
   *
   * @param ex
   * @return　エラーメッセージ
   */
  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  /**
   * バリデーションエラー(@void)による例外を処理する。
   *
   * @param ex
   * @return　エラーメッセージ
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body("入力エラー： " + ex.getMessage());
  }

  /**
   * その他一般的な例外を処理する。
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleOtherException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("サーバーエラー：" + ex.getMessage());
  }

}
