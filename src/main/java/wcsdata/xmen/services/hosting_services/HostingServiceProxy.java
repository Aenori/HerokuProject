package wcsdata.xmen.services.hosting_services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HostingServiceProxy {
    @Value("${force_s3_hosting}")
    boolean force_s3_hosting;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private IHostingService hostingService;

    public IHostingService getHostingService() {
        if (hostingService == null) {
            initHostingService();
        }
        return hostingService;
    }

    private void initHostingService() {
        if(force_s3_hosting || activeProfile.equals("prod")) {
            hostingService = new AmazonS3HostingService();
        }
        else {
            hostingService = new LocalHostingService();
        }
    }
}
