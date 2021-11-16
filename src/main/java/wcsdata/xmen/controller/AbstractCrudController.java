package wcsdata.xmen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        Map<String, Object> model = new HashMap<String, Object>();
        E e = getElement(sr.pathVariable("id"));
        postProcessUpdateResult(e);
        
        model.put("element", e);
        model.put("controllerRoute", getControllerRoute());

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .render(getControllerRoute() + "/update",
                        model);
    }

    public ServerResponse update(ServerRequest sr) throws ServletException, IOException {
        Map<String, String> map = sr.params().toSingleValueMap();
        map.remove("_csrf");

        ObjectMapper mapper = new ObjectMapper();
        E e = mapper.convertValue(map, getElementClass());
        postProcessUpdateResult(e);

        getRepository().save(e);

        return ServerResponse.permanentRedirect(URI.create(getControllerRoute()))
                .build();
    }

    protected void postProcessUpdateResult(E e) {}

    protected void postProcessElementForUpdateGet(E e) {}

    protected E getElement(String id) {
        return getRepository().getById(parseId(id));
    }

    protected RouterFunction<ServerResponse> getRoutes() {
        RouterFunction<ServerResponse> newRoute = route()
                .GET(getControllerRoute(), accept(MediaType.ALL), this::getAll)
                .GET(getControllerRoute() + "/{id}/update", accept(MediaType.ALL), this::updateGet)
                .GET("api/" + getControllerRoute(), accept(MediaType.ALL), this::getAllJson)
                //.POST(getControllerRoute(), accept(MediaType.ALL), this::create)
                .PUT(getControllerRoute() + "/{id}", accept(MediaType.ALL), this::update)
                .PATCH(getControllerRoute() + "/{id}", accept(MediaType.ALL), this::update)
                //.DELETE(getControllerRoute() + "/{id}", accept(MediaType.ALL), this::delete)
                .build();

        return newRoute;
    }
}
