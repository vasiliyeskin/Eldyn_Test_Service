package ru.web.ets.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import ru.web.ets.dto.UserTo;
import ru.web.ets.model.Role;
import ru.web.ets.model.User;

public class UserUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getFirstname(), newUser.getLastname(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setFirstname(userTo.getFirstname());
        user.setLastname(userTo.getLastname());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}