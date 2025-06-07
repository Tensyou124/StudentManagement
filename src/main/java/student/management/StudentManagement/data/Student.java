package student.management.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
public class Student {

  @Schema(description = "受講生ID", example = "1")
  private String id;

  @Schema(description = "名前", example = "渡邊侑也")
  @NotBlank(message = "名前を入力してください。")
  private String name;

  @Schema(description = "フリガナ", example = "ワタナベユウヤ")
  @NotBlank(message = "フリガナを入力してください。")
  private String furigana;

  @Schema(description = "ニックネーム", example = "おじさん")
  private String nickname;

  @Schema(description = "メールアドレス", example = "????@example.com")
  @Email(message = "メールアドレスの形式が間違っています。")
  private String email;

  @Schema(description = "地域", example = "本宮市")
  private String area;

  @Schema(description = "年齢", example = "18")
  @Min(value = 0,message = "年齢は0以上にしてください。")
  private int age;

  @Schema(description = "性別", example = "男")
  @NotBlank(message = "性別は「男」「女」「その他」のいずれかを入力してください。")
  @Pattern(regexp = "^(男|女|その他)?$",message = "性別は「男」「女」「その他」のいずれかにしてください。")
  private String gender;

  @Schema(description = "備考", example = "JAVAベーシック受講を考え中")
  private String remark;

  private Boolean isDeleted; //booleanをBooleanに変更することでエラーが出なくなった。

  private List<StudentCourse> studentsCourse;
}

