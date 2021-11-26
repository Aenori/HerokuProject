package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.CerebookPost;
import wcsdata.xmen.repository.CerebookUserRepository;
import wcsdata.xmen.repository.CerebookPostRepository;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/db")
    String db(Map<String, Object> model) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            model.put("records", output);
            return "db";
        } catch (Exception e) {
            throw e;
        }
    }


    @RequestMapping("/api/endpoints")
    @ResponseBody
    public Object showEndpointsAction() throws SQLException
    {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(t ->
                (t.getMethodsCondition().getMethods().size() == 0 ? "GET" : t.getMethodsCondition().getMethods().toArray()[0]) + " " +
                        t.getPatternsCondition().getPatterns().toArray()[0]
        ).sorted().toArray();
    }
}
