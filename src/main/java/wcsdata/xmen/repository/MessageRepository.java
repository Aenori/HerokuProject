package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
