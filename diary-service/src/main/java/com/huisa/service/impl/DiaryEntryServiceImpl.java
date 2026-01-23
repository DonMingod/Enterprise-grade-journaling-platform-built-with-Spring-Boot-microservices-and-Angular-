package com.huisa.service.impl;


import com.huisa.dto.DiaryEntryRequest;
import com.huisa.dto.DiaryEntryResponse;
import com.huisa.exception.ResourceNotFound;
import com.huisa.model.DiaryEntry;
import com.huisa.repository.DiaryEntryRepository;
import com.huisa.service.DiaryEntryService;
import com.huisa.util.DiaryEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryEntryServiceImpl  implements DiaryEntryService {

    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryMapper diaryEntryMapper;


    @Override
    public List<DiaryEntryResponse> getAllDiaryEntries() {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findAll());
    }

    @Override
    public DiaryEntryResponse getDiaryEntryById(Long id) {
        return diaryEntryMapper.toDto(diaryEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Diary entry not found with id: " + id)
        ));
    }

    @Override
    public List<DiaryEntryResponse> getDiaryEntriesByUserId(Long userId) {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @Override
    public List<DiaryEntryResponse> getFavoriteDiaryEntries(Long userId) {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findByUserIdAndIsFavorite(userId, true));
    }

    @Override
    public List<DiaryEntryResponse> getDiaryEntriesByMood(Long userId, String mood) {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findByUserIdAndMood(userId, mood));
    }

    @Override
    public List<DiaryEntryResponse> getDiaryEntriesByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findByUserIdAndCreatedAtBetween(userId, startDate, endDate));
    }

    @Override
    public List<DiaryEntryResponse> searchDiaryEntries(Long userId, String keyword) {
        return diaryEntryMapper.toDtoList(diaryEntryRepository.findByUserIdAndTitleContainingIgnoreCase(userId, keyword));
    }

    @Override
    public DiaryEntryResponse createDiaryEntry(DiaryEntryRequest request) {
        return diaryEntryMapper.toDto(diaryEntryRepository.save(diaryEntryMapper.toEntity(request)));
    }

    @Override
    public DiaryEntryResponse updateDiaryEntry(Long id, DiaryEntryRequest request) {
        DiaryEntry diaryEntryFound = diaryEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Diary entry not found with id: " + id)
        );
        diaryEntryFound.setTitle(request.title());
        diaryEntryFound.setContent(request.content());
        diaryEntryFound.setUserId(request.userId());
        diaryEntryFound.setMood(request.mood());
        diaryEntryFound.setLocation(request.location());
        diaryEntryFound.setWeather(request.weather());
        diaryEntryFound.setTags(request.tags());
        diaryEntryFound.setIsFavorite(request.isFavorite());
        diaryEntryFound.setIsPrivate(request.isPrivate());

        return diaryEntryMapper.toDto(diaryEntryRepository.save(diaryEntryFound));
    }

    @Override
    public void deleteDiaryEntry(Long id) {
        DiaryEntry diaryEntryFound = diaryEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Diary entry not found with id: " + id)
        );
        diaryEntryRepository.delete(diaryEntryFound);
    }


}
