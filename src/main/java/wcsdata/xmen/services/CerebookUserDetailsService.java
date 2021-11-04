package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import wcsdata.xmen.repository.CerebookUserRepository;

@Service
public class CerebookUserDetailsService implements UserDetailsService {
    @Autowired
    private CerebookUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
