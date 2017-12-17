package ru.web.ets.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.web.ets.model.User;
import ru.web.ets.util.exception.NotFoundException;

import static ru.web.ets.UserTestData.*;

public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        /*User user = service.getWithMeals(USER_ID);
        assertMatch(user, USER);
        MealTestData.assertMatch(user.getMeals(), MealTestData.MEALS);*/
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
//        service.getWithMeals(1);
    }
}