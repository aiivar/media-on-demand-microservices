package ru.itis.aivar.streaming.video.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.streaming.video.models.Video;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoInfoDto {

    private String uuid;
    private String title;

    public static VideoInfoDto from(Video video) {
        return VideoInfoDto.builder()
                .uuid(video.getId())
                .title(video.getTitle())
                .build();
    }

    public static List<VideoInfoDto> from(Collection<Video> videos) {
        return videos.stream().map(VideoInfoDto::from).collect(Collectors.toList());
    }

}
