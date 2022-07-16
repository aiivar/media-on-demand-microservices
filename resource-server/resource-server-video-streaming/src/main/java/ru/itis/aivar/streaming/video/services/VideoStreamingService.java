package ru.itis.aivar.streaming.video.services;

import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;
import ru.itis.aivar.streaming.video.dto.form.VideoPartForm;

public interface VideoStreamingService {
    VideoResponseMessage getVideoPart(VideoPartForm videoPartForm);
}
