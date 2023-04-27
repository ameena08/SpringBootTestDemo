package com.example.SpringBootTestDemo.controller;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService mockStudentService;

    @Test
    void testAddStudent() throws Exception {
        // Setup
        // Configure StudentService.addStudent(...).
        final Student student = new Student(0, "name", "address", "rollno");
        when(mockStudentService.addStudent(new Student(0, "name", "address", "rollno"))).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/student/")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllStudent() throws Exception {
        // Setup
        // Configure StudentService.getAllStudent(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentService.getAllStudent()).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllStudent_StudentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentService.getAllStudent()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetStudentById() throws Exception {
        // Setup
        // Configure StudentService.getStudentById(...).
        final Student student = new Student(0, "name", "address", "rollno");
        when(mockStudentService.getStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteStudentById() throws Exception {
        // Setup
        // Configure StudentService.deleteStudentById(...).
        final Student student = new Student(0, "name", "address", "rollno");
        when(mockStudentService.deleteStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetStudentByName() throws Exception {
        // Setup
        // Configure StudentService.getStudentByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentService.getStudentByName("name")).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-name}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetStudentByName_StudentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentService.getStudentByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-name}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
