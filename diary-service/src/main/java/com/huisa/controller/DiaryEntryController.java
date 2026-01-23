package com.huisa.controller;

import com.huisa.dto.DiaryEntryRequest;
import com.huisa.service.DiaryEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/diary-entries")
@RestController
@RequiredArgsConstructor
public class DiaryEntryController {

    private final DiaryEntryService diaryEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(diaryEntryService.getAllDiaryEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(diaryEntryService.getDiaryEntryById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(diaryEntryService.getDiaryEntriesByUserId(userId));
    }

    @GetMapping("/user/{userId}/favorites")
    public ResponseEntity<?> getFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(diaryEntryService.getFavoriteDiaryEntries(userId));
    }

    @GetMapping("/user/{userId}/mood/{mood}")
    public ResponseEntity<?> getByMood(@PathVariable Long userId, @PathVariable String mood) {
        return ResponseEntity.ok(diaryEntryService.getDiaryEntriesByMood(userId, mood));
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<?> getByDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(diaryEntryService.getDiaryEntriesByDateRange(userId, startDate, endDate));
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<?> search(@PathVariable Long userId, @RequestParam String keyword) {
        return ResponseEntity.ok(diaryEntryService.searchDiaryEntries(userId, keyword));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DiaryEntryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diaryEntryService.createDiaryEntry(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DiaryEntryRequest request) {
        return ResponseEntity.ok(diaryEntryService.updateDiaryEntry(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        diaryEntryService.deleteDiaryEntry(id);
        return ResponseEntity.noContent().build();
    }
}
