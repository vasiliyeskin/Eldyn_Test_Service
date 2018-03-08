package ru.web.ets.service;

import ru.web.ets.model.forDocs.Student;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTestData {
    public static final int Student_ID = 4;

    public static final Student std = new Student(Student_ID, "first", "first", "ufirst", 1, "teacher@yandex.ru");

    public static void assertMatch(Student actual, Student expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "phone");
    }

    public static void assertMatch(Iterable<Student> actual, Student... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Student> actual, Iterable<Student> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "phone").isEqualTo(expected);
    }

}
