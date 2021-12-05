package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wcsdata.xmen.services.hosting_services.HostingServiceProxy;
import wcsdata.xmen.services.hosting_services.IHostingService;

import java.io.InputStream;
import java.util.List;

/* NRO 2021-12-03 : This class only deals with the physical hosting
     of the file
 */
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

    public void uploadFile(String objectKey, InputStream inputStream, Long size) {
        getHostingService().uploadFile(objectKey, inputStream, size);
    }

    public List<String> listObjects() {
        return getHostingService().listObjects();
    }

    public void deleteObject(String key) {
        getHostingService().deleteObject(key);
    }

    public String getUrlPrefix() {
        return getHostingService().getUrlPrefix();
    }

    public boolean isAmazon() {
        return getHostingService().isAmazon();
    }
}
