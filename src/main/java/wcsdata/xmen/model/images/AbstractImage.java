package wcsdata.xmen.model.images;

public abstract class AbstractImage {
    private String imagePath;

    public AbstractImage(String imagePath) {
        this.imagePath = imagePath;
    }

    protected String getImage(int width, int height) {
        return String.format("%s_%s_%s.png", imagePath, width, height);
    }
}
