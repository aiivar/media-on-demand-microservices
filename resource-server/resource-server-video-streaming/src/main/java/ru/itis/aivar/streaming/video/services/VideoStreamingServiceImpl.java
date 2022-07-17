package ru.itis.aivar.streaming.video.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.itis.aivar.media.messaging.serializable.VideoRequestMessage;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;
import ru.itis.aivar.streaming.video.dto.form.VideoPartForm;

@Service
@AllArgsConstructor
public class VideoStreamingServiceImpl implements VideoStreamingService {

    private static final long CHUNK_SIZE_MB = 1L;

    private static final String ROUTING_KEY = "video.streaming.partly";

    private RabbitTemplate rabbitTemplate;

    private TopicExchange topicExchange;

    @Override
    public VideoResponseMessage getVideoPart(VideoPartForm videoPartForm) {
        VideoRequestMessage videoRequestMessage = VideoRequestMessage.builder()
                .mediaFormat(videoPartForm.getVideoFormat())
                .uuid(videoPartForm.getUuid())
                .bytesStart(videoPartForm.getStart())
                .bytesChunkSize(megaBytesToBytes(CHUNK_SIZE_MB*5))
                .build();

        return sendMessageAndReceiveResponse(videoRequestMessage);
    }

    private VideoResponseMessage sendMessageAndReceiveResponse(VideoRequestMessage videoRequestMessage) {
        return (VideoResponseMessage) rabbitTemplate.convertSendAndReceive(topicExchange.getName(), ROUTING_KEY, videoRequestMessage);
    }

    private long megaBytesToBytes(long megaBytes) {
        return megaBytes * 1024 * 1024;
    }
}
