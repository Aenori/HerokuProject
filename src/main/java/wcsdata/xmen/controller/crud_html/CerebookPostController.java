package wcsdata.xmen.controller.crud_html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wcsdata.xmen.entity.CerebookPost;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookPostRepository;
import wcsdata.xmen.repository.CerebookUserRepository;
import wcsdata.xmen.services.AdressesApiService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts")
public class CerebookPostController
        extends AbstractCrudHtmlIntegerController<CerebookPost> {
    @Autowired
    private CerebookPostRepository cerebookPostRepository;

    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Autowired
    private AdressesApiService adressesApiService;

    @Override
    protected JpaRepository<CerebookPost, Integer> getRepository() {
        return cerebookPostRepository;
    }

    @Override
    protected String[] getElementFields() {
        return new String[]{"content", "author"};
    }

    @Override
    protected void preProcessElement(CerebookPost cerebookPost, HttpServletRequest hsr) {
        if (hsr.getParameter("author") != null &&
                !hsr.getParameter("author").isEmpty()) {
            cerebookPost.setAuthor(
                cerebookUserRepository.getById(
                    Integer.parseInt(hsr.getParameter("author"))
                )
            );
        }
    }

    @Override
    protected Class<CerebookPost> getElementClass() {
        return CerebookPost.class;
    }
}
