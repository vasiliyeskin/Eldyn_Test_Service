package ru.web.ets.web.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.service.forDocs.AdviserService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/curator")
public class AdminAjaxCuratorController {

    @Autowired
    private AdviserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScientificAdviser> getAll() {
        return service.getCurator();
    }
}
