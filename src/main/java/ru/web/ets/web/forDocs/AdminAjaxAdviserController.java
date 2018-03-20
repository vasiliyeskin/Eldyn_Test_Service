package ru.web.ets.web.forDocs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.ScientificAdviser;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/advisers")
public class AdminAjaxAdviserController extends AbstractAdviserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScientificAdviser> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ScientificAdviser get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("firstname") String firstname,
                               @RequestParam("middlename") String middlename,
                               @RequestParam("lastname") String lastname,
                               @RequestParam("org") Integer organizationID,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("position") Integer positionID) {

        ScientificAdviser adviser = new ScientificAdviser(id, firstname, middlename, lastname, email, phone);
        adviser.setOrganization(super.getOrg(organizationID));
        adviser.setPosition(super.getPosition(positionID));
        if (adviser.isNew()) {
            super.create(adviser);
        } else {
            super.update(adviser, adviser.getId());
        }
    }
}
