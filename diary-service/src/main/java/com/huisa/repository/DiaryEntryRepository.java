package com.huisa.repository;

import com.huisa.model.DiaryEntry;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryEntryRepository extends JpaRepository< @NonNull DiaryEntry, @NonNull Long> {


    List<DiaryEntry> findByUserId(Long userId);
    List<DiaryEntry> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<DiaryEntry> findByUserIdAndIsFavorite(Long userId, Boolean isFavorite);
    List<DiaryEntry> findByUserIdAndMood(Long userId, String mood);
    List<DiaryEntry> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<DiaryEntry> findByUserIdAndTitleContainingIgnoreCase(Long userId, String keyword);

}
