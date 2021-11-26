package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.model.UserDetailsWrapper;
import wcsdata.xmen.repository.CerebookUserRepository;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.security.Principal;

public abstract class AbstractCrudController<E, EK> {
    // <editor-fold desc="Abstract methods">
    protected abstract JpaRepository<E, EK> getRepository();

    protected abstract EK parseId(String id);
    protected abstract String[] getElementFields();
    // </editor-fold>

    // <editor-fold desc="Route methods">
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("allElements", getRepository().findAll());
        addCommonModel(model);

        return getControllerRoute() + "/getAll";
    }

    @PostMapping("")
    public String create(HttpServletRequest hsr, @ModelAttribute E e) {
        preProcessElement(e, hsr);
        getRepository().save(e);

        return "redirect:/" + getControllerRoute();
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") String id, Model model) {
        model.addAttribute("element", getElement(id));
        addCommonModel(model);

        return getControllerRoute() + "/getOne";
    }

    @GetMapping("/{id}/update")
    public String updateGet(@PathVariable("id") String id, Model model) {
        E e = getElement(id);
        postProcessElementForUpdateGet(e);
        
        model.addAttribute("element", e);
        addCommonModel(model);

        return getControllerRoute() + "/update";
    }

    private void addCommonModel(Model model) {
        model.addAttribute("elementFields", getElementFields());
        model.addAttribute("controllerRoute", getControllerRoute());
    }

    @PostMapping("/{id}/update")
    public String update(HttpServletRequest hsr, @PathVariable("id") String id, @ModelAttribute E e) {
        preProcessElement(e, hsr);
        getRepository().save(e);

        return "redirect:/" + getControllerRoute();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id) {
        getRepository().deleteById(parseId(id));

        return "redirect:/" + getControllerRoute();
    }
    // </editor-fold>

    // <editor-fold desc="Sub methods">
    protected void preProcessElement(E e, HttpServletRequest hsr) {}
    protected void postProcessElementForUpdateGet(E e) {}
    protected E getElement(String id) {
        return getRepository().getById(parseId(id));
    }

    protected CerebookUser getLoggerUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(o instanceof  UserDetailsWrapper) {
            return ((UserDetailsWrapper) o).getCerebookUser();
        }
        return null;
    }
    // </editor-fold>

    protected String getControllerRoute() {
        for(Annotation annotation : this.getClass().getAnnotations()) {
            if(annotation instanceof RequestMapping) {
                return ((RequestMapping) annotation).value()[0].substring(1);
            }
        }

        return null;
    }
}
