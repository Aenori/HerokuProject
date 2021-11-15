package wcsdata.xmen.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Controller
public class TestCrudController extends CrudController<String> {

    ServerResponse getAllCerebookUser(ServerRequest sr) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello world !");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(stringList);
    }

    @Bean
    RouterFunction<ServerResponse> hello() {
        HandlerFunction<ServerResponse> hf = this::getAllCerebookUser;
        RouterFunction<ServerResponse> newRoute = route()
                .GET("/cerebookUsersTest2", accept(MediaType.ALL), hf)
                .build();

        return newRoute;
    }
}
