package wcsdata.xmen.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import wcsdata.xmen.entity.CerebookUser;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

public abstract class AbstractCrudController<E, EK> {
    protected abstract CrudRepository<E, EK> getRepository();
    protected abstract String getControllerRoute();
    public ServerResponse getAllJson(ServerRequest sr) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getRepository().findAll());
    }

    public ServerResponse getAll(ServerRequest sr) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(getRepository().findAll());
    }

    RouterFunction<ServerResponse> getRoutes() {
        RouterFunction<ServerResponse> newRoute = route()
                .GET(getControllerRoute() + "/api/getAll", accept(MediaType.ALL), this::getAllJson)
                .GET(getControllerRoute() + "/getAll", accept(MediaType.ALL), this::getAll)
                .build();

        return newRoute;
    }
}
