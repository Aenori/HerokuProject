package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.AppUser;
import wcsdata.xmen.repository.AppUserRepository;

@Service
public class CerebookUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("Received username : " + username);
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CerebookUser(appUser);
    }

}
