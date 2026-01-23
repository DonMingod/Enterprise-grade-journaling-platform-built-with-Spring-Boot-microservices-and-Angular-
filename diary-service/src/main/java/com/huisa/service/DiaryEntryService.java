package com.huisa.service;

import com.huisa.dto.DiaryEntryRequest;
import com.huisa.dto.DiaryEntryResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryEntryService {


    List<DiaryEntryResponse> getAllDiaryEntries();

    DiaryEntryResponse getDiaryEntryById(Long id);

    List<DiaryEntryResponse> getDiaryEntriesByUserId(Long userId);

    List<DiaryEntryResponse> getFavoriteDiaryEntries(Long userId);

    List<DiaryEntryResponse> getDiaryEntriesByMood(Long userId, String mood);

    List<DiaryEntryResponse> getDiaryEntriesByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    List<DiaryEntryResponse> searchDiaryEntries(Long userId, String keyword);

    DiaryEntryResponse createDiaryEntry(DiaryEntryRequest request);

    DiaryEntryResponse updateDiaryEntry(Long id, DiaryEntryRequest request);

    void deleteDiaryEntry(Long id);
}
