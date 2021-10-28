package wcsdata.xmen.repository;

import org.springframework.data.repository.CrudRepository;
import wcsdata.xmen.entity.Post;

public interface PostDAO extends CrudRepository<Post, Integer> {
}
