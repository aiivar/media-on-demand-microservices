package ru.itis.aivar.media.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VideoStreamingService implements StreamingService {

    private static final String FORMAT = "classpath:video/%s.mp4";

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Mono<Resource> getVideo(String id) {
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT, id)));
    }
}
