package ru.itis.aivar.streaming.video.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;
import ru.itis.aivar.streaming.video.dto.form.VideoPartForm;
import ru.itis.aivar.streaming.video.services.VideoInfoService;
import ru.itis.aivar.streaming.video.services.VideoStreamingService;

@RestController
@AllArgsConstructor
public class VideoController {

    private VideoStreamingService videoStreamingService;
    private VideoInfoService videoInfoService;

    @GetMapping("/video/{uuid}/{format}")
    public ResponseEntity<byte[]> getVideoPart(@PathVariable String uuid, @PathVariable String format, @RequestHeader("Range") String contentRange) {
        VideoResponseMessage videoResponseMessage = getVideoPartFromService(uuid, format, contentRange);
        return buildVideoPartResponse(videoResponseMessage);
    }

    private ResponseEntity<byte[]> buildVideoPartResponse(VideoResponseMessage videoResponseMessage) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentLength(videoResponseMessage.getEnd() - videoResponseMessage.getStart() + 1)
                .contentType(MediaType.parseMediaType(String.format("video/%s", videoResponseMessage.getMediaFormat())))
                .header("Accept-Ranges", "bytes")
                .header("Content-Range", String.format(
                        "bytes %s-%s/%s",
                        videoResponseMessage.getStart(),
                        videoResponseMessage.getEnd(),
                        videoResponseMessage.getVideoSize()
                ))
                .body(videoResponseMessage.getBytes());
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
        contentRange = contentRange.split("=")[1];
        String start = contentRange.split("-")[0];
        return Long.parseLong(start);
    }

}
