package wcsdata.xmen.services.hosting_services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import wcsdata.xmen.entity.CerebookMedia;
import wcsdata.xmen.model.HostedMedia;
import wcsdata.xmen.model.SimpleHostedMedia;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class AmazonS3HostingService implements IHostingService {
    // <editor-fold desc="Fields">
    @Value("${amazon.aws.s3.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.s3.secretkey}")
    private String accessSecret;

    @Value("${amazon.aws.s3.bucket}")
    private String bucketName;

    @Value("https://${amazon.aws.s3.bucket}.s3.${amazon.aws.s3.region}.amazonaws.com/")
    private String urlPrefix;

    private AmazonS3 amazonS3;

    private Map<CerebookMedia.Type, Function<CerebookMedia, HostedMedia>> mediaConstructorMap;
    // </editor-fold>

    // <editor-fold desc="Public methods">
    public AmazonS3 getAmazonS3() {
        if(amazonS3 == null) {
            initAmazonS3Client();
        }
        return amazonS3;
    }

    public void uploadPictureImage(String objectKey, InputStream inputStream, Long size) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        getAmazonS3().putObject(
                new PutObjectRequest(bucketName, objectKey, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public List<HostedMedia> buildMedia(List<CerebookMedia> cerebookMediaList) {
        return cerebookMediaList
                .stream()
                .map(cm -> getMediaConstructorMap().get(cm.getMediaType()).apply(cm))
                .collect(Collectors.toList());
    }

    public List<String> listObjects() {
        return getAmazonS3()
                .listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public void deleteObject(String key) {
        getAmazonS3().deleteObject(new DeleteObjectRequest(bucketName, key));
    }
    // </editor-fold>

    // <editor-fold desc="Private methods">
    private void initAmazonS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                accessSecret
        );

        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    private Map<CerebookMedia.Type, Function<CerebookMedia, HostedMedia>> getMediaConstructorMap() {
        if(mediaConstructorMap == null) {
            mediaConstructorMap = new HashMap<>();
            mediaConstructorMap.put(
                    CerebookMedia.Type.SimpleMedia,
                    cm -> new SimpleHostedMedia(urlPrefix + cm.getObjectKey())
            );
        }
        return mediaConstructorMap;
    }

    @Override
    public String toString() {
        return "AmazonS3HostingService{" +
                "accessKey='" + accessKey + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", urlPrefix='" + urlPrefix + '\'' +
                '}';
    }
    // </editor-fold>
}
