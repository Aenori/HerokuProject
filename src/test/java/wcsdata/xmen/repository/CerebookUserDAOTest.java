package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wcsdata.xmen.entity.CerebookUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class CerebookUserDAOTest {
    @Autowired
    private CerebookUserDAO cerebookUserDao;

    @Test
    public void findByName() {
        assertNull(cerebookUserDao.findByName("Darth Vador"));
        assertEquals("James Logan Howlett",
                cerebookUserDao.findByName("Wolverine").getHumanName());
    }

    @Test
    public void save() {
        assertEquals(1, cerebookUserDao.count());
        assertThat(cerebookUserDao.findByName("Cyclope")).isNull();

        cerebookUserDao.save(new CerebookUser(
                "Cyclope", "Scott Summers"));

        assertThat(cerebookUserDao.findByName("Cyclope")).isNotNull();
        assertEquals("Scott Summers",
                cerebookUserDao.findByName("Cyclope").getHumanName());
    }
}