package wcsdata.xmen.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;

@Controller
@RequestMapping("/users")
public class CerebookUserController
        extends AbstractCrudIntegerController<CerebookUser> {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<CerebookUser, Integer> getRepository() {
        return cerebookUserRepository;
    }

    @Override
    protected String getControllerRoute() { return "users"; }

    @Override
    protected String[] getElementFields() {
        return new String[]{"username", "password",
            "humanName", "name"
        };
    }

    @Override
    protected void preProcessElement(CerebookUser cerebookUser, HttpServletRequest _hsr) {
        updatePassword(cerebookUser);
        try {
            processProfilePicture(cerebookUser, _hsr.getPart("uploadedProfilePicture"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void processProfilePicture(CerebookUser cerebookUser, Part file) {
        String originalFilename = file.getSubmittedFileName();
        String fileName = String.format(
                "/tmp/profile_%s.%s",
                cerebookUser.getId(),
                FilenameUtils.getExtension(originalFilename)
        );

        try(OutputStream outputStream = new FileOutputStream(fileName)){
            IOUtils.copy(file.getInputStream(), outputStream);
        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }
    }

    private void updatePassword(CerebookUser cerebookUser) {
        if(cerebookUser.getPassword().isEmpty()) {
            cerebookUser.setPassword(
                    cerebookUserRepository.getById(cerebookUser.getId())
                            .getPassword());
        }
        else {
            cerebookUser.setPassword(
                    passwordEncoder.encode(cerebookUser.getPassword()));
        }
    }

    @Override
    protected void postProcessElementForUpdateGet(CerebookUser cerebookUser) {
        cerebookUser.setPassword(null);
    }
}
