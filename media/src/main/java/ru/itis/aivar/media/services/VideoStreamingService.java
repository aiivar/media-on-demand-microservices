package ru.itis.aivar.media.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.itis.aivar.media.messaging.serializable.VideoRequestMessage;
import ru.itis.aivar.media.messaging.serializable.VideoResponseMessage;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Service
@AllArgsConstructor
@Slf4j
public class VideoStreamingService implements StreamingService {

    private static final String FORMAT = "classpath:video/%s.%s";

    private ResourceLoader resourceLoader;

    @Override
    public VideoResponseMessage getVideoPart(VideoRequestMessage videoRequestMessage) throws IOException {
        Resource resource = resourceLoader.getResource(String.format(
                FORMAT,
                videoRequestMessage.getUuid(),
                videoRequestMessage.getMediaFormat())
        );
        byte[] bytes = readBytesInRange(resource.getFile(), videoRequestMessage.getBytesStart(), videoRequestMessage.getBytesChunkSize());
        VideoResponseMessage videoResponseMessage = VideoResponseMessage.builder()
                .bytes(bytes)
                .start(videoRequestMessage.getBytesStart())
                .end(videoRequestMessage.getBytesStart() + bytes.length - 1)
                .videoSize(resource.contentLength())
                .mediaFormat(videoRequestMessage.getMediaFormat())
                .build();
        log.info("Response data: {}/{} - bytes {} - start {} - end {}",
                videoRequestMessage.getUuid(), videoResponseMessage.getMediaFormat(),
                bytes.length,
                videoResponseMessage.getStart(), videoResponseMessage.getEnd());
        return videoResponseMessage;
    }

    private byte[] readBytesInRange(File sourceFile, long startingOffset, long length) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(sourceFile, "r")) {
            long available = randomAccessFile.length() - startingOffset;
            byte[] buffer;
            if (available < length) {
                buffer = new byte[(int) available];
            } else {
                buffer = new byte[(int) length];
            }
            randomAccessFile.seek(startingOffset);
            randomAccessFile.readFully(buffer);

            return buffer;
        } catch (EOFException e) {
            log.error("End of file", e);
            return new byte[0];
        }
    }
}
