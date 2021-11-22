package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.CerebookMessage;
import wcsdata.xmen.entity.CerebookPost;

public interface CerebookMessageRepository extends JpaRepository<CerebookMessage, Integer> {
}
