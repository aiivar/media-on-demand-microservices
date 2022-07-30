package ru.itis.aivar.media.messaging.serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VideoRequestMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String uuid;
    private long bytesStart;
    private long bytesChunkSize;
    private String mediaFormat;

}
