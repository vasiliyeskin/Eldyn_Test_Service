package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.web.ets.model.forDocs.Organization;
import ru.web.ets.model.forDocs.PositionInTheOrganization;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.service.forDocs.AdviserService;
import ru.web.ets.service.forDocs.OrganizationService;
import ru.web.ets.service.forDocs.PositionService;

import java.util.List;

public abstract class AbstractAdviserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdviserService service;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PositionService positionService;

    public List<ScientificAdviser> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public ScientificAdviser get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public ScientificAdviser create(ScientificAdviser adviser) {
        log.info("create {}", adviser);
        return service.create(adviser);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(ScientificAdviser adviser, int id) {
        log.info("update {} with id={}", adviser, id);
        service.update(adviser);
    }

    public Organization getOrg(Integer orgID)
    {
        return organizationService.get(orgID);
    }

    public PositionInTheOrganization getPosition(Integer posID) {
        return positionService.get(posID);
    }


    public void setCurator(int id, boolean iscurator) {
        service.setCurator(id, iscurator);
    }
}
