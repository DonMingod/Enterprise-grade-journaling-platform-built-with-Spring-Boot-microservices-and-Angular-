package com.huisa.util;

import com.huisa.dto.DiaryEntryRequest;
import com.huisa.dto.DiaryEntryResponse;
import com.huisa.model.DiaryEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;


@Mapper(componentModel = "spring")
public interface DiaryEntryMapper {

    DiaryEntry toEntity(DiaryEntryRequest request);
    DiaryEntryResponse toDto(DiaryEntry entity);
    List<DiaryEntryResponse> toDtoList(List<DiaryEntry> entities);
}
