package ru.itis.aivar.streaming.video.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoPartForm {

    private String uuid;
    private String videoFormat;
    private long start;

}
