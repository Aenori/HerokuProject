package wcsdata.xmen.services.hosting_services;

import java.io.*;
import java.nio.file.Files;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractHostingTest {
    public static void genericTest(IHostingService iHostingService) {
        assertFalse(iHostingService.listObjects().contains("image_for_test.png"));
        File testFile = new File("src/test/resources/image_for_test.png");
        try (InputStream inputStream = new FileInputStream(
                testFile
        )) {
            iHostingService.uploadPictureImage(
                    "image_for_test.png",
                    inputStream,
                    testFile.length()
            );
        }
        catch (IOException io) {
            fail("IOException caught");
        }

        assertTrue(iHostingService.listObjects().contains("image_for_test.png"));
        iHostingService.deleteObject("image_for_test.png");
        assertFalse(iHostingService.listObjects().contains("image_for_test.png"));
    }
}
