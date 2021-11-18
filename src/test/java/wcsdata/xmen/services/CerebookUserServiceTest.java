package wcsdata.xmen.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.repository.CerebookUserRepository;

@ActiveProfiles("test")
class CerebookUserServiceTest {
    // @Autowired
    // CerebookUserService cerebookUserService;

    // @Autowired
    // CerebookUserRepository cerebookUserRepository;

    @Test
    public void findFriendSuggestions() {
        //CerebookUser cerebookUser = cerebookUserRepository.findByUsername("superman");
        //cerebookUserService.findFriendSuggestions(cerebookUser);
    }
}