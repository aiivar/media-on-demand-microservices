package ru.itis.aivar.streaming.video.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;
import ru.itis.aivar.streaming.video.dto.form.VideoPartForm;
import ru.itis.aivar.streaming.video.services.VideoInfoService;
import ru.itis.aivar.streaming.video.services.VideoStreamingService;

@Controller
@AllArgsConstructor
public class VideoController {

    private VideoStreamingService videoStreamingService;
    private VideoInfoService videoInfoService;

    @GetMapping("/video/{uuid}/{format}")
    public ResponseEntity<byte[]> getVideoPart(@PathVariable String uuid, @PathVariable String format, @RequestHeader("Range") String contentRange) {
        VideoResponseMessage videoResponseMessage = getVideoPartFromService(uuid, format, contentRange);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new byte[0]);
    }

    private VideoResponseMessage getVideoPartFromService(String uuid, String format, String contentRange) {
        return videoStreamingService.getVideoPart(
                VideoPartForm.builder()
                        .uuid(uuid)
                        .videoFormat(format)
                        .start(parseStart(contentRange))
                        .build()
        );
    }

    private long parseStart(String contentRange) {
        String start = contentRange.split("-")[0];
        return Long.parseLong(start);
    }

}
