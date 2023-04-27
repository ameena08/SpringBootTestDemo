package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exceptions.StudentNotFoundException;
import com.example.SpringBootTestDemo.repository.Studentrepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    private StudentServiceImpl studentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        studentServiceImplUnderTest = new StudentServiceImpl();
        studentServiceImplUnderTest.repo = mock(Studentrepo.class);
    }

    @Test
    void testAddStudent() {
        // Setup
        final Student student = new Student(0, "name", "address", "rollno");
        final Student expectedResult = new Student(0, "name", "address", "rollno");

        // Configure Studentrepo.save(...).
        final Student student1 = new Student(0, "name", "address", "rollno");
        when(studentServiceImplUnderTest.repo.save(new Student(0, "name", "address", "rollno"))).thenReturn(student1);

        // Run the test
        final Student result = studentServiceImplUnderTest.addStudent(student);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollno"));

        // Configure Studentrepo.findAll(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(studentServiceImplUnderTest.repo.findAll()).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent_StudentrepoReturnsNoItems() {
        // Setup
        when(studentServiceImplUnderTest.repo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetStudentById() {
        // Setup
        final Student expectedResult = new Student(0, "name", "address", "rollno");

        // Configure Studentrepo.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollno"));
        when(studentServiceImplUnderTest.repo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.getStudentById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentById_StudentrepoReturnsAbsent() {
        // Setup
        when(studentServiceImplUnderTest.repo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.getStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testDeleteStudentById() {
        // Setup
        // Configure Studentrepo.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollno"));
        when(studentServiceImplUnderTest.repo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.deleteStudentById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(studentServiceImplUnderTest.repo).delete(new Student(0, "name", "address", "rollno"));
    }

    @Test
    void testDeleteStudentById_StudentrepoFindByIdReturnsAbsent() {
        // Setup
        when(studentServiceImplUnderTest.repo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.deleteStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testGetStudentByName() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollno"));

        // Configure Studentrepo.findByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(studentServiceImplUnderTest.repo.findByName("name")).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentByName_StudentrepoReturnsNoItems() {
        // Setup
        when(studentServiceImplUnderTest.repo.findByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
