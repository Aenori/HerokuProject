package wcsdata.xmen.repository;


import org.springframework.data.repository.CrudRepository;
import wcsdata.xmen.entity.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
