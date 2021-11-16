package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Controller
public class CerebookUserController extends AbstractCrudController<CerebookUser, Integer> {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Override
    protected JpaRepository<CerebookUser, Integer> getRepository() {
        return cerebookUserRepository;
    }

    @Override
    protected String getControllerRoute() {
        return "cerebookUser";
    }

    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }

    protected Class<CerebookUser> getElementClass() {
        return CerebookUser.class;
    }

    @Bean
    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return super.getRoutes();
    }
}
