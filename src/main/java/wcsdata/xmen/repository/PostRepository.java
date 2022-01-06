package wcsdata.xmen.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
