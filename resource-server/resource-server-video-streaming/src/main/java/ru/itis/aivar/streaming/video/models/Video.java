package ru.itis.aivar.streaming.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    private String id;
    private String title;

    public static Video newInstance() {
        return Video.builder()
                .id(UUID.randomUUID().toString())
                .build();
    }

}
