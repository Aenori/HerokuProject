package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.CerebookUser;

public interface CerebookUserRepository extends JpaRepository<CerebookUser, Integer> {
    CerebookUser findByUsername(String username);
    CerebookUser findByName(String name);
}

