package student.management.StudentManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourse;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.repository.StudentsRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentsRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧機能_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    StudentService sut = new StudentService(repository, converter);
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studnetCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studnetCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studnetCourseList);
  }

  @Test
  void 受講生詳細の検索機能_リポジトリの呼び出しと戻り値の確認() {
    String studentId = "10";
    Student mockStudent = new Student();
    mockStudent.setId(studentId);

    List<StudentCourse> mockCourseList = new ArrayList<>();
    when(repository.searchStudent(studentId)).thenReturn(mockStudent);
    when(repository.searchStudentCourse(studentId)).thenReturn(mockCourseList);

    sut.searchStudent(studentId);

    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);
  }

  @Test
  void 受講生詳細の新規追加機能_受講生とコース情報が正しく登録されること() {
    Student student = new Student();
    student.setId("10");

    StudentCourse course = new StudentCourse();
    List<StudentCourse> courseList = List.of(course);

    StudentDetail detail = new StudentDetail(student, courseList);

    sut.updateStudent(detail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourse(course);
  }

  @Test
  void 受講生コース初期化処理_日付とstudentIdが正しく設定されること() {
    Student student = new Student();
    student.setId("10");

    StudentCourse course = new StudentCourse();
    List<StudentCourse> courseList = List.of(course);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentCourseList(courseList);

    sut.registerStudent(detail);

    assertEquals("10", course.getStudentId());

    LocalDate today = LocalDate.now();
    assertEquals(today, course.getCourseStartDate());
    assertEquals(today.plusYears(1), course.getCourseEndDate());
  }

  @Test
  void 受講生詳細の更新機能_受講生とコース情報が正しくリポジトリに渡されること() {
    Student student = new Student();
    student.setId("10");

    StudentCourse course1 = new StudentCourse();
    StudentCourse course2 = new StudentCourse();

    List<StudentCourse> courseList = List.of(course1, course2);
    StudentDetail detail = new StudentDetail(student, courseList);

    sut.updateStudent(detail);

    verify(repository, times(1)).updateStudent(student);

    verify(repository, times(1)).updateStudentCourse(course1);
    verify(repository, times(1)).updateStudentCourse(course2);
  }
}