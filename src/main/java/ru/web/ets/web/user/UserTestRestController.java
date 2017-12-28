package ru.web.ets.web.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.UserTest;
import ru.web.ets.service.UserTestService;

import org.slf4j.Logger;

@Controller
public class UserTestRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserTestService userTestService;

    public UserTest get(int id) {
        log.info("get test {}", id);
        return userTestService.get(id);
    }

    public UserTest create(UserTest test) {
        return userTestService.create(test, AuthorizedUser.id());
    }

    public UserTest save(UserTest userTest) {
        log.info("update {} with id={}", userTest, userTest.getId());
        return userTestService.update(userTest, AuthorizedUser.id());
    }
}
