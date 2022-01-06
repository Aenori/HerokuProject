package wcsdata.xmen.entity;

import javax.persistence.*;

@Entity
public class Media {
    public enum Type {
        SimpleMedia,
        ResizedPicture
    }

    // <editor-fold desc="Fields">
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String objectKey;
    private Type mediaType;

    public boolean isAmazonS3Hosted() {
        return amazonS3Hosted;
    }

    public void setAmazonS3Hosted(boolean amazonS3Hosted) {
        this.amazonS3Hosted = amazonS3Hosted;
    }

    private boolean amazonS3Hosted;
    // </editor-fold>

    // <editor-fold desc="Constructor">
    public Media() {}
    public Media(String objectKey, Type mediaType) {
        this.mediaType = mediaType;
        this.objectKey = objectKey;
    }
    public Media(String objectKey, String mediaType) {
        this(objectKey, Type.valueOf(mediaType));
    }
    public Media(String objectKey, Type mediaType, boolean amazonS3Hosted) {
        this.mediaType = mediaType;
        this.objectKey = objectKey;
        this.amazonS3Hosted =amazonS3Hosted;
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
