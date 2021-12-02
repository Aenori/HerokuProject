package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wcsdata.xmen.entity.CerebookMedia;
import wcsdata.xmen.services.hosting_services.HostingServiceProxy;
import wcsdata.xmen.services.hosting_services.IHostingService;

import java.io.InputStream;
import java.util.List;

@Service
public class HostingService {
    @Autowired
    private HostingServiceProxy hostingServiceProxy;

    private IHostingService hostingService;

    private IHostingService getHostingService() {
        if(hostingService == null) {
            hostingService = hostingServiceProxy.getHostingService();
        }
        return hostingService;
    }

    public Object buildMediaList(List<CerebookMedia> all) {
        return null;
    }

    public void uploadPictureImage(String objectKey, InputStream inputStream, Long size) {
        getHostingService().uploadPictureImage(objectKey, inputStream, size);
    }

    public List<String> listObjects() {
        return getHostingService().listObjects();
    }
}
