package wcsdata.xmen.services.hosting_services;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wcsdata.xmen.services.hosting_services.AmazonS3HostingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
@ActiveProfiles("test")
class AmazonS3HostingServiceTest {
    @Autowired
    AmazonS3HostingService amazonS3HostingService; // = new AmazonS3HostingService();

    @Value("${amazon.aws.s3.accesskey}")
    private String s3_access_key;

    @Test
    void putAndListBuckets() {
        // Don't run the test if S3 is not set
        Assumptions.assumeTrue(!s3_access_key.isEmpty(), "S3 env variables are not set");

        assertEquals("https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/", amazonS3HostingService.getUrlPrefix());

        AbstractHostingTest.genericTest(amazonS3HostingService);
    }
}
