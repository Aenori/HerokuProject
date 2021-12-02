package wcsdata.xmen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wcsdata.xmen.entity.CerebookMedia;
import wcsdata.xmen.model.SimpleHostedMedia;
import wcsdata.xmen.repository.CerebookMediaRepository;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaService {
    @Autowired
    CerebookMediaRepository cerebookMediaRepository;

    @Autowired
    HostingService hostingService;

    public void uploadMedia(String filename, InputStream inputStream, long size) {
        hostingService.uploadPictureImage(filename, inputStream, size);

        cerebookMediaRepository.save(
                new CerebookMedia(
                    filename,
                    CerebookMedia.Type.SimpleMedia,
                    hostingService.isAmazon()
                )
        );
    }

    public List<SimpleHostedMedia> getMediaList() {
        return cerebookMediaRepository
                .findAll()
                .stream()
                .map(cm -> new SimpleHostedMedia(
                        hostingService.getUrlPrefix(),
                        cm
                ))
                .collect(Collectors.toList());
    }
}
