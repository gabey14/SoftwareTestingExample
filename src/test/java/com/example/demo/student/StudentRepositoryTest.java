package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {

//        given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila", email, Gender.FEMALE
        );
        underTest.save(student);

//        when
        boolean expectedval = underTest.selectExistsEmail(email);

//        then
        assertThat(expectedval).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {

//        given
        String email = "jamila@gmail.com";

//        when
        boolean expectedval = underTest.selectExistsEmail(email);
//        then
        assertThat(expectedval).isFalse();
    }
}