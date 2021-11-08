package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wcsdata.xmen.entity.CerebookUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class CerebookUserRepositoryTest {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

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
}