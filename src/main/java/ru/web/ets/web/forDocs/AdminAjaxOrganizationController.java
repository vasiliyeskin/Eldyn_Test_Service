package ru.web.ets.web.forDocs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.Organization;
import ru.web.ets.model.forDocs.Student;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/organizations")
public class AdminAjaxOrganizationController extends AbstractOrganizationController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Organization> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Organization get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("nameGenitive") String nameGenitive,
                               @RequestParam("shortname") String shortname) {

        Organization org = new Organization(id, name, nameGenitive, shortname);
        if (org.isNew()) {
            super.create(org);
        } else {
            super.update(org, org.getId());
        }
    }
}
