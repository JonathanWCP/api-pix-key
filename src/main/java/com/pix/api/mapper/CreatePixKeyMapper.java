package com.pix.api.mapper;

import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreatePixKeyMapper {

    CreatePixKeyMapper INSTANCE = Mappers.getMapper(CreatePixKeyMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datetimeInclusion", ignore = true)
    @Mapping(target = "datetimeInactivation", ignore = true)
    PixKey createPixKeyRequestToPixKey(CreatePixKeyRequest createPixKeyRequest);

}
