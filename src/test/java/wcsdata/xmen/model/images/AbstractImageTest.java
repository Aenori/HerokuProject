package wcsdata.xmen.model.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractImageTest {
    class TestImage extends AbstractImage {
        public TestImage(String imagePath) {
            super(imagePath);
        }

        public String getImage(int width, int height) {
            return super.getImage(width, height);
        }
    }

    @Test
    public void getImage() {
        assertEquals(
                "myProfileImage_150_200.png",
                new TestImage("myProfileImage")
                        .getImage(150, 200)
        );
    }
}