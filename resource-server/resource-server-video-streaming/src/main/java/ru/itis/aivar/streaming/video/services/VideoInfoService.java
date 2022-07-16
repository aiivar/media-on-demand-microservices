package ru.itis.aivar.streaming.video.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.aivar.streaming.video.dto.VideoInfoDto;
import ru.itis.aivar.streaming.video.exceptions.VideoNotFoundException;
import ru.itis.aivar.streaming.video.repositories.VideoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoInfoService {

    private VideoRepository videoRepository;

    public List<VideoInfoDto> getAllVideoInfos() {
        return VideoInfoDto.from(videoRepository.findAll());
    }

    public VideoInfoDto getVideoInfo(String uuid) {
        return VideoInfoDto.from(videoRepository.findById(uuid).orElseThrow(VideoNotFoundException::new));
    }

}
