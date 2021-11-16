package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.Post;
import wcsdata.xmen.model.UserDetailsWrapper;
import wcsdata.xmen.repository.CerebookUserRepository;
import wcsdata.xmen.repository.PostRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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
public class SampleController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CerebookUserRepository cerebookUserDao;

    @Autowired
    private PostRepository postDao;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @GetMapping("/cerebookUsers")
    @ResponseBody
    List<CerebookUser> getAllCerebookUser() {
        List<CerebookUser> cerebookUsers = new ArrayList<>();
        cerebookUserDao.findAll().forEach(cerebookUsers::add);

        return cerebookUsers;
    }

    @GetMapping("/cerebookPosts")
    @ResponseBody
    List<Post> getAllCerebookPosts() {
        List<Post> cerebookPost = new ArrayList<>();
        postDao.findAll().forEach(cerebookPost::add);

        return cerebookPost;
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

    @ResponseBody
    @RequestMapping("/admin")
    Map<String, String> admin(
            SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper,
            Principal principal
    ) {
        if(!securityContextHolderAwareRequestWrapper.isUserInRole("ROLE_admin")) {
            throw new AccessDeniedException("403 forbidden");
        }
        Map<String, String> result = new HashMap<>();
        result.put("message", "Welcome admin");

        return result;
    }
}
