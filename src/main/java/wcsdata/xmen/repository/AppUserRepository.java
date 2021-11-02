package wcsdata.xmen.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import wcsdata.xmen.entity.AppUser;

public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    AppUser findByUsername(String userName);
}

