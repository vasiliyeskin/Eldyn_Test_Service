package ru.web.ets;

import ru.web.ets.model.Role;
import ru.web.ets.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.web.ets.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int TEACH_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "User", "Smith", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User TEACHER = new User(TEACH_ID, "T1000", "T2", "teacher@yandex.ru", "password", Role.ROLE_ADMIN);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "Smith", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles", "meals");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles", "meals").isEqualTo(expected);
    }
}
