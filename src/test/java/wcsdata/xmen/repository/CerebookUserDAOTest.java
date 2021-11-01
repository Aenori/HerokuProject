package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CerebookUserDAOTest {
    @Autowired
    private CerebookUserDAO cerebookUserDao;

    @Test
    public void findByName() {
        assertNull(cerebookUserDao.findByName("Darth Vador"));
        assertEquals("James Logan Howlett",
                cerebookUserDao.findByName("Wolverine").getHumanName());
    }
}