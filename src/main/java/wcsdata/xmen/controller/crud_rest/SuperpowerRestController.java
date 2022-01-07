package wcsdata.xmen.controller.crud_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.Superpower;
import wcsdata.xmen.repository.SuperpowerRepository;

import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://web_ng:4200"})
@RequestMapping("/api/superpowers")
public class SuperpowerRestController extends AbstractCrudRestIntegerController<Superpower> {
    @Autowired
    SuperpowerRepository superpowerRepository;

    @Override
    protected JpaRepository<Superpower, Integer> getRepository() {
        return superpowerRepository;
    }

    @Override
    protected String[] getElementFields() {
        return new String[]{"name"};
    }

    @Override
    protected Class<Superpower> getElementClass() {
        return Superpower.class;
    }

    @GetMapping("{id}/users")
    public Set<CerebookUser> getUsers(@PathVariable("id") Integer id) {
        return superpowerRepository.getById(id).getCerebookUsers();
    }
}
