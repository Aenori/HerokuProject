package wcsdata.xmen.services.hosting_services;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
class LocalHostingServiceTest {
    @Autowired
    LocalHostingService localHostingService;

    @Test
    void putAndListBuckets() {
        // Don't run the test if S3 is not set
        assertEquals("/uploads", localHostingService.getUrlPrefix());

        AbstractHostingTest.genericTest(localHostingService);
    }
}