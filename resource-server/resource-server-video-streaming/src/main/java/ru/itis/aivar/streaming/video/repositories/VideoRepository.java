package ru.itis.aivar.streaming.video.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.aivar.streaming.video.models.Video;

public interface VideoRepository extends JpaRepository<Video, String> {

}
