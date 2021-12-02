package wcsdata.xmen.model;

import wcsdata.xmen.entity.CerebookMedia;

public class SimpleHostedMedia {
    private final String urlPrefix;
    private final CerebookMedia cerebookMedia;

    public SimpleHostedMedia(String urlPrefix, CerebookMedia cerebookMedia) {
        this.urlPrefix = urlPrefix;
        this.cerebookMedia = cerebookMedia;
    }

    public String getUrl() {
        return urlPrefix + "/" + cerebookMedia.getObjectKey();
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public CerebookMedia getCerebookMedia() {
        return cerebookMedia;
    }
}
