package ru.itis.aivar.media.messaging.serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoResponseMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private byte[] bytes;
    private long videoSize;
    private long start;
    private long end;
    private String mediaFormat;

}
