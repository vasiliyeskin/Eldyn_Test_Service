package ru.web.ets.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.service.TestService;
import static ru.web.ets.TestsTestData.*;
import static ru.web.ets.UserTestData.USER_ID;
import static ru.web.ets.UserTestData.ADMIN_ID;
import static ru.web.ets.UserTestData.TEACH_ID;

public class DataJpaTestServiceTest extends AbstractServiceTest {

    @Autowired
    protected TestService service;

    @Test
    public void create() throws Exception {
        ru.web.ets.model.Test newtest = new ru.web.ets.model.Test(null, "adfd", null);
        ru.web.ets.model.Test created = service.create(newtest, USER_ID );
        newtest.setId(created.getId());
        assertMatch(service.getAll(), newtest);
    }

}
