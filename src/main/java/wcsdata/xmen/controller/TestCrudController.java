package wcsdata.xmen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


public class TestCrudController extends CrudController<String> {
    public static String getPrefix() {
        return "cerebookUsersTest";
    }
    List<String> getAllCerebookUser() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello world !");

        return stringList;
    }
}
