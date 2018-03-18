package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.Organization;
import ru.web.ets.service.forDocs.OrganizationService;

import java.util.List;

public abstract class AbstractOrganizationController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrganizationService service;

    public List<Organization> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Organization get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Organization create(Organization org) {
        log.info("create {}", org);
        return service.create(org);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Organization org, int id) {
        log.info("update {} with id={}", org, id);
        service.update(org);
    }
}
