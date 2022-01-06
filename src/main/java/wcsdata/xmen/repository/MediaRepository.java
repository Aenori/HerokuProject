package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}

