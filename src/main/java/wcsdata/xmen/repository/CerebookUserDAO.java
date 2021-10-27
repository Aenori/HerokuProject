package wcsdata.xmen.repository;

import org.springframework.data.repository.CrudRepository;
import wcsdata.xmen.entity.CerebookUser;

public interface CerebookUserDAO extends CrudRepository<CerebookUser, Long> {
    CerebookUser findByName(String name);
}

