package wcsdata.xmen.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.model.UserDetailsWrapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

public abstract class AbstractCrudController<E, EK> {
    // <editor-fold desc="Abstract methods">
    protected abstract JpaRepository<E, EK> getRepository();
    protected abstract EK parseId(String id);
    protected abstract String[] getElementFields();
    protected abstract Class<E> getElementClass();
    // </editor-fold>

    // <editor-fold desc="Sub methods">
    protected void preProcessElement(E e, HttpServletRequest hsr) {}
    protected void postProcessElementForUpdateGet(E e) {}
    protected E getElement(String id) {
        return getRepository().getById(parseId(id));
    }

    protected CerebookUser getLoggerUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(o instanceof UserDetailsWrapper) {
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
