package wcsdata.xmen.model;

public class SimpleHostedMedia implements HostedMedia {
    private final String url;

    @Override
    public String getMainPicture() {
        return url;
    }

    public SimpleHostedMedia(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
