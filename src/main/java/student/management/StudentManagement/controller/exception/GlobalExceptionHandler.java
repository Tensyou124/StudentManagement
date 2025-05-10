package student.management.StudentManagement.controller.exception;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * バリデーションエラー(@void)による例外を処理する。
   *
   * @param ex
   * @return　エラーメッセージ
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
    String errorMessages = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .distinct()
        .collect(Collectors.joining("\n"));
    return ResponseEntity.badRequest().body("入力エラー：\n\n" + errorMessages);
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
        .body("サーバーエラー：\n" + ex.getMessage());
  }

}
