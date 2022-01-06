package wcsdata.xmen.model;

import wcsdata.xmen.entity.Media;

public class SimpleHostedMedia {
    private final String urlPrefix;
    private final Media cerebookMedia;

    public SimpleHostedMedia(String urlPrefix, Media cerebookMedia) {
        this.urlPrefix = urlPrefix;
        this.cerebookMedia = cerebookMedia;
    }

    public String getUrl() {
        return urlPrefix + "/" + cerebookMedia.getObjectKey();
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public Media getCerebookMedia() {
        return cerebookMedia;
    }
}
