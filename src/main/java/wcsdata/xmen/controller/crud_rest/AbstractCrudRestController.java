package wcsdata.xmen.controller.crud_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wcsdata.xmen.controller.AbstractCrudController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://web_ng:4200"})
public abstract class AbstractCrudRestController<E, EK> extends AbstractCrudController<E, EK> {

    @GetMapping("")
    public List<E> getAll() {
        return getRepository().findAll();
    }

    @GetMapping("/{id}")
    public E getOne(@PathVariable("id") String id) {
        return getRepository().findById(
                parseId(id)
        ).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND, getNotFoundMessage(id)
        ));
    }

    @PostMapping("")
    public E addOne(@ModelAttribute E e, HttpServletRequest hsr) {
        preProcessElement(e, hsr);
        return getRepository().save(e);
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public E updateOne(
            @PathVariable("id") String id,
            @ModelAttribute E e,
            HttpServletRequest hsr
            ) {
        preProcessElement(e, hsr);
        return getRepository().save(e);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") String id) {
        try {
            getRepository().deleteById(
                    parseId(id)
            );
        }
        catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, getNotFoundMessage(id));
        }
    }

    protected String getNotFoundMessage(String id) {
        return "Entity " + getElementClass().getName() + "with id " + id + " not found";
    }
}
