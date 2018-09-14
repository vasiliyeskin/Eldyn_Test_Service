package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.PositionInTheOrganization;
import ru.web.ets.service.forDocs.PositionService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/position")
public class AdminAjaxPositionController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PositionService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PositionInTheOrganization> getAll() {
        log.info("getAll");
        return service.getAll();
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PositionInTheOrganization get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("positionIO") String positionIO) {

        PositionInTheOrganization position = new PositionInTheOrganization(id, positionIO);

        if (position.isNew()) {
            create(position);
        } else {
            update(position, position.getId());
        }
    }

    public PositionInTheOrganization create(PositionInTheOrganization position) {
        log.info("create {}", position);
        return service.create(position);
    }

    public void update(PositionInTheOrganization position, int id) {
        log.info("update {} with id={}", position, id);
        service.update(position);
    }
}
