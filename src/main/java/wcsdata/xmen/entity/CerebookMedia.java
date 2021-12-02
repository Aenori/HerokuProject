package wcsdata.xmen.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
public class CerebookMedia {
    public static enum Type {
        SimpleMedia,
        ResizedPicture
    }

    // <editor-fold desc="Fields">
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String objectKey;
    private Type mediaType;
    private boolean amazonS3Hosted;
    // </editor-fold>

    // <editor-fold desc="Constructor">
    public CerebookMedia() {}
    public CerebookMedia(String objectKey, Type mediaType) {
        this.mediaType = mediaType;
        this.objectKey = objectKey;
    }
    public CerebookMedia(String objectKey, String mediaType) {
        this(objectKey, Type.valueOf(mediaType));
    }
    public CerebookMedia(String objectKey, Type mediaType, boolean amazonS3Hosted) {
        this.mediaType = mediaType;
        this.objectKey = objectKey;
    }
    // </editor-fold>

    // <editor-fold desc="Getter-Setter">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getMediaType() {
        return mediaType;
    }

    public void setMediaType(Type mediaType) {
        this.mediaType = mediaType;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
    // </editor-fold>
}
