package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.CerebookUserFriends;
import wcsdata.xmen.entity.CerebookPost;
import wcsdata.xmen.entity.ids.CerebookUserFriendsId;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class CerebookUserRepositoryTest {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Autowired
    private CerebookUserFriendsRepository cerebookUserFriendsRepository;

    @Autowired
    private CerebookPostRepository postRepository;

    @Test
    public void findByName() {
        assertNull(cerebookUserRepository.findByName("Darth Vador"));
        assertEquals("James Logan Howlett",
                cerebookUserRepository.findByName("Wolverine").getHumanName());
    }

    @Test
    public void save() {
        long currentUsersCount = cerebookUserRepository.count();
        assertThat(cerebookUserRepository.findByName("Cyclope")).isNull();

        cerebookUserRepository.save(new CerebookUser(
                "cyclope", null, "Cyclope", "Scott Summers"));

        assertThat(cerebookUserRepository.findByName("Cyclope")).isNotNull();
        assertEquals("Scott Summers",
                cerebookUserRepository.findByName("Cyclope").getHumanName());
        assertEquals(currentUsersCount + 1, cerebookUserRepository.count());
    }

    @Test
    public void cascadePost() {
        CerebookUser superman = cerebookUserRepository.findByUsername("superman");
        assertThat(superman).isNotNull();

        long postCount = postRepository.count();
        CerebookPost post = superman.createPost();
        post.setContent("Hello world !");
        cerebookUserRepository.save(superman);

        assertThat(postRepository.count()).isEqualTo(postCount + 1);

        postRepository.delete(post);

        superman = cerebookUserRepository.findByUsername("superman");
        assertThat(superman).isNotNull();
    }

    @Test
    public void manyToMany() {
        CerebookUser superman = cerebookUserRepository.findByUsername("superman");
        assertThat(superman).isNotNull();

        long allFriendsCount = cerebookUserFriendsRepository.count();
        superman.getFriends().add(superman);
        cerebookUserRepository.save(superman);

        assertThat(cerebookUserFriendsRepository.count()).isEqualTo(
                allFriendsCount + 1
        );

        Optional<CerebookUserFriends> cerebookUserFriends = cerebookUserFriendsRepository.findById(new CerebookUserFriendsId(
                superman, superman
        ));

        assertThat(cerebookUserFriends).isPresent();
        assertThat(cerebookUserFriends.get().getCreatedAt()).isNull();
    }
}