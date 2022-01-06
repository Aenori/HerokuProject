package wcsdata.xmen.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wcsdata.xmen.entity.Message;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class MessageRepositoryTest {
    @Autowired
    private CerebookUserRepository cerebookUserRepository;

    @Autowired
    private MessageRepository cerebookMessageRepository;

    @Test
    void addRecipient() {
        Message message = cerebookMessageRepository.getById(1);
        assertEquals(0, message.getRecipient().size());
        message.getRecipient().add(cerebookUserRepository.getById(1));
        cerebookMessageRepository.save(message);

        message = cerebookMessageRepository.getById(1);
        assertEquals(1, message.getRecipient().size());
    }
}