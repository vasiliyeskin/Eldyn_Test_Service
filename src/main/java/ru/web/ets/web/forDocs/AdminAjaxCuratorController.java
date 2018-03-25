package ru.web.ets.web.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping(value = "/getDocsForCurator")
    public String getDocsForCurator(@RequestParam("practice") Integer practiceId,
                                    @RequestParam("curator") Integer curatorId,
                                    @RequestParam("trainingDirection") Integer trainingDirectionId,
                                    @RequestParam("course") Integer courseId) {

        String s = "Ok";
//        ScientificAdviser adviser = new ScientificAdviser(id, firstname, middlename, lastname, email, phone);
//        adviser.setOrganization(super.getOrg(organizationID));
//        adviser.setPosition(super.getPosition(positionID));
//        if (adviser.isNew()) {
//            super.create(adviser);
//        } else {
//            super.update(adviser, adviser.getId());
//        }
        return s;
    }
}
