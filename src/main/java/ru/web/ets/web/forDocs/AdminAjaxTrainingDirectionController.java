package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.TrainingDirection;
import ru.web.ets.service.forDocs.TrainingDirectionService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/td")
public class AdminAjaxTrainingDirectionController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrainingDirectionService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainingDirection> getAll() {
        log.info("getAll");
        return service.getAll();
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TrainingDirection get(@PathVariable("id") int id) {
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
                               @RequestParam("td") String tdCU) {

        TrainingDirection td = new TrainingDirection(id, tdCU);

        if (td.isNew()) {
            create(td);
        } else {
            update(td, td.getId());
        }
    }

    public TrainingDirection create(TrainingDirection td) {
        log.info("create {}", td);
        return service.create(td);
    }

    public void update(TrainingDirection td, int id) {
        log.info("update {} with id={}", td, id);
        service.update(td);
    }
}
