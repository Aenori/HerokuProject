package wcsdata.xmen.controller.crud_html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class CerebookUserHtmlController
        extends AbstractCrudHtmlIntegerController<CerebookUser> {
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

    @Override
    protected Class<CerebookUser> getElementClass() {
        return CerebookUser.class;
    }
}
