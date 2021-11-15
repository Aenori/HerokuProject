package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wcsdata.xmen.repository.CerebookUserRepository;

@Controller
@RequestMapping("/cerebookUser")
public class CerebookUserController {
    @Autowired
    private CerebookUserRepository cerebookUserDao;

    public String create() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path="/getAll")
    public String request(Model model) {
        model.addAttribute("cerebook_users", cerebookUserDao.findAll());

        return "cerebook_users";
    }

    public String update() {
        return null;
    }

    public String delete() {
        return null;
    }
}
