package ru.itis.aivar.media.services;

import ru.itis.aivar.media.messaging.serializable.VideoRequestMessage;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;

import java.io.IOException;

public interface StreamingService {
    VideoResponseMessage getVideoPart(VideoRequestMessage videoRequestMessage) throws IOException;
}
