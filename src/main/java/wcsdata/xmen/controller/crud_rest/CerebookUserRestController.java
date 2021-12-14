package wcsdata.xmen.controller.crud_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://web_ng:4200"})
@RequestMapping("/api/users")
public class CerebookUserRestController
        extends AbstractCrudRestIntegerController<CerebookUser> {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<CerebookUser, Integer> getRepository() {
        return cerebookUserRepository;
    }

    @Override
    protected String[] getElementFields() {
        return new String[]{"username", "password",
            "humanName", "name"
        };
    }

    @Override
    protected void preProcessElement(CerebookUser cerebookUser, HttpServletRequest _hsr) {
        String password = cerebookUser.getPassword();
        if(password == null || password.isEmpty()) {
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

    @Override
    protected Class<CerebookUser> getElementClass() {
        return CerebookUser.class;
    }
}
