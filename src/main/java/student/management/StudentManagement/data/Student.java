package student.management.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
  private String id;

  @NotBlank(message = "名前を入力してください。")
  private String name;

  @NotBlank(message = "フリガナを入力してください。")
  private String furigana;

  private String nickname;

  @Email(message = "メールアドレスの形式が間違っています。")
  private String email;

  private String area;

  @Min(value = 0,message = "年齢は0以上にしてください。")
  private int age;

  @NotBlank(message = "性別は「男」「女」「その他」のいずれかを入力してください。")
  @Pattern(regexp = "^(男|女|その他)?$",message = "性別は「男」「女」「その他」のいずれかにしてください。")
  private String gender;

  private String remark;

  private Boolean isDeleted; //booleanをBooleanに変更することでエラーが出なくなった。

  private List<StudentCourse> studentsCourse;
}

