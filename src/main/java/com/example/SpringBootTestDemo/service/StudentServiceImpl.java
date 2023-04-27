package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exceptions.StudentNotFoundException;
import com.example.SpringBootTestDemo.repository.Studentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public Studentrepo repo;

    @Override
    public Student addStudent(Student student) {
        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> s = repo.findById(id);
        if (!s.isPresent()) {
            throw new StudentNotFoundException("student with id" + id + "is not found");
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public Student deleteStudentById(int id) {
        Optional<Student> s = repo.findById(id);
        if (!s.isPresent()) {
            throw new StudentNotFoundException("student with id" + id + "is not found");
        }
         repo.delete(s.get());

        return null;
    }
    @Override
    public List<Student> getStudentByName(String name) {
        return repo.findByName(name);
    }
}
