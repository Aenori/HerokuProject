package wcsdata.xmen.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;
import reactor.core.publisher.Mono;
import wcsdata.xmen.entity.CerebookUser;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

public abstract class AbstractCrudController<E, EK> {
    protected abstract JpaRepository<E, EK> getRepository();
    protected abstract String getControllerRoute();
    protected abstract EK parseId(String id);
    protected abstract Class<E> getElementClass();

    public ServerResponse getAllJson(ServerRequest sr) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getRepository().findAll());
    }

    public ServerResponse getAll(ServerRequest sr) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("allElements", getRepository().findAll());
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .render(getControllerRoute() + "/getAll",
                        model);
    }

    public ServerResponse updateGet(ServerRequest sr) {
        System.out.println("Hello => " + sr.pathVariable("id"));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("element", getRepository().getById(parseId(sr.pathVariable("id"))));
        model.put("controllerRoute", getControllerRoute());

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .render(getControllerRoute() + "/update",
                        model);
    }

    public ServerResponse update(ServerRequest sr) throws ServletException, IOException {
        
        getRepository().save(sr.body(getElementClass()));

        return ServerResponse.permanentRedirect(URI.create(getControllerRoute() + "/getAll"))
                .build();
    }



    protected RouterFunction<ServerResponse> getRoutes() {
        RouterFunction<ServerResponse> newRoute = route()
                .GET(getControllerRoute() + "/api/getAll", accept(MediaType.ALL), this::getAllJson)
                .GET(getControllerRoute() + "/getAll", accept(MediaType.ALL), this::getAll)
                .GET(getControllerRoute() + "/{id}/update", accept(MediaType.ALL), this::updateGet)
                .POST(getControllerRoute() + "/update", accept(MediaType.ALL), this::update)
                .build();

        return newRoute;
    }
}
