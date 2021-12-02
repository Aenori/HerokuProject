package wcsdata.xmen.services.hosting_services;

import wcsdata.xmen.entity.CerebookMedia;

import java.io.InputStream;
import java.util.List;

class LocalHostingService implements IHostingService {

    @Override
    public void uploadPictureImage(String objectKey, InputStream inputStream, Long size) {
    }

    @Override
    public List<String> listObjects() {
        return null;
    }

    @Override
    public void deleteObject(String key) {

    }
}
