package wcsdata.xmen.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import wcsdata.xmen.entity.CerebookUser;

public interface CerebookUserDAO extends PagingAndSortingRepository<CerebookUser, Long> {
    CerebookUser findByName(String name);
}

