package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wcsdata.xmen.entity.CerebookUser;
import wcsdata.xmen.entity.CerebookUserFriends;
import wcsdata.xmen.entity.Post;
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
    private PostRepository postRepository;

    @Test
    public void findByName() {
        assertNull(cerebookUserRepository.findByName("Darth Vador"));
        assertEquals("James Logan Howlett",
                cerebookUserRepository.findByName("Wolverine").getHumanName());
    }

    @Test
    public void save() {
        long currentUsersCount = cerebookUserRepository.count();
        assertThat(cerebookUserRepository.findByName("DeadPool")).isNull();

        cerebookUserRepository.save(new CerebookUser(
                "deadpool", null, "DeadPool", "Scott Summers"));

        assertThat(cerebookUserRepository.findByName("DeadPool")).isNotNull();
        assertEquals("Scott Summers",
                cerebookUserRepository.findByName("DeadPool").getHumanName());
        assertEquals(currentUsersCount + 1, cerebookUserRepository.count());
    }

    @Test
    public void cascadePost() {
        CerebookUser superman = cerebookUserRepository.findByUsername("superman");
        assertThat(superman).isNotNull();

        long postCount = postRepository.count();
        Post post = superman.createPost();
        post.setContent("Hello world !");
        cerebookUserRepository.save(superman);

        assertThat(postRepository.count()).isEqualTo(postCount + 1);

        postRepository.delete(post);

        superman = cerebookUserRepository.findByUsername("superman");
        assertThat(superman).isNotNull();
    }

    @Test
    public void getFriends() {
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
    }

    @Test
    void findFriendsSuggestions() {
        CerebookUser wolverine = cerebookUserRepository.getById(1);
        CerebookUser jeanGrey = cerebookUserRepository.getById(2);
        CerebookUser cyclope = cerebookUserRepository.getById(3);

        wolverine.addFriend(jeanGrey);
        cyclope.addFriend(jeanGrey);

        // wolverine.getFriends().add(jeanGrey);
        // cyclope.getFriends().add(jeanGrey);

        cerebookUserRepository.save(wolverine);
        cerebookUserRepository.save(cyclope);

        assertEquals(
                1,
                cerebookUserRepository.findFriendsSuggestions(wolverine).size()
        );
    }
}