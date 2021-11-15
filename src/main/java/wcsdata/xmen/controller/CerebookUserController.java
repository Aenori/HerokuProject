package wcsdata.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/cerebookUser")
public class CerebookUserController {
    @Autowired
    private CerebookUserRepository cerebookUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public String create(@ModelAttribute CerebookUser cerebookUser) {
        if(cerebookUser.getId() != null) {
            throw new AccessDeniedException("403 forbidden");
        }

        cerebookUser.setPassword(passwordEncoder.encode(
                cerebookUser.getPassword()
        ));
        cerebookUserDao.save(cerebookUser);

        return "redirect:/cerebookUser/getAll";
    }

    @RequestMapping(method = RequestMethod.GET, path="/getAll")
    public String request(Model model) {
        model.addAttribute("cerebookUsers", cerebookUserDao.findAll());

        CerebookUser cerebookUser = new CerebookUser();
        cerebookUser.setName("xmen name");

        model.addAttribute("newCerebookUser", cerebookUser);
        return "cerebook_users";
    }

    @RequestMapping(method = RequestMethod.GET, path="/api/getAll")
    @ResponseBody
    public List<CerebookUser> request() {
        return cerebookUserDao.findAll();
    }

    public String update() {
        return null;
    }

    public String delete() {
        return null;
    }

    @PostMapping("/uploadProfile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), Paths.get("data/" + file.getOriginalFilename()));

        return "redirect:/cerebookUser/getAll";
    }
}
