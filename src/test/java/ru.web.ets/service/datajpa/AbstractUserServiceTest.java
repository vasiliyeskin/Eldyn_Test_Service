package ru.web.ets.service.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.web.ets.model.Role;
import ru.web.ets.model.User;
import ru.web.ets.repository.JpaUtil;
import ru.web.ets.service.UserService;
import ru.web.ets.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.web.ets.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

/*    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected JpaUtil jpaUtil;*/

    @Before
    public void setUp() throws Exception {
        /*cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();*/
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "New", "new@gmail.com", "newPass",  false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setFirstname("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "  ","mail@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "User","  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "User","mail@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "User","mail@yandex.ru", "password",  true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "User","mail@yandex.ru", "password", true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
    }
}