package ru.itis.aivar.media.listeners;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.itis.aivar.media.messaging.serializable.VideoRequestMessage;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;
import ru.itis.aivar.media.services.StreamingService;

@Component
@AllArgsConstructor
@Slf4j
public class VideoRequestListener {

    private StreamingService streamingService;

    @RabbitListener(queues = "#{videoQueue.name}")
    public VideoResponseMessage processVideoPartRequest(VideoRequestMessage videoRequestMessage) {
        try {
            return streamingService.getVideoPart(videoRequestMessage);
        } catch (Exception e) {
            log.error("Message error", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
