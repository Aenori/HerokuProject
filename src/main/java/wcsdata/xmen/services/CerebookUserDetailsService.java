package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.model.UserDetailsWrapper;
import wcsdata.xmen.repository.CerebookUserRepository;

@Service
public class CerebookUserDetailsService implements UserDetailsService {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        CerebookUser user = cerebookUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsWrapper(user);
    }
}
