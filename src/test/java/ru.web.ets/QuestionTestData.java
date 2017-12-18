package ru.web.ets;

import ru.web.ets.model.Question;

import java.util.Arrays;

import static ru.web.ets.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTestData {
    public static final int TEST1_ID = START_SEQ + 2;
    public static final int ADMIN_TEST_ID = START_SEQ + 8;


    public static Question getCreated() {
        return new Question(null, "Созданный вопрос", null);
    }

    public static Question getUpdated() {
        return new Question(TEST1_ID, "Обновленный вопрос", null);
    }

    public static void assertMatch(Question actual, Question expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Question> actual, Question... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Question> actual, Iterable<Question> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

}
