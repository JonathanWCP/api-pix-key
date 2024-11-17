package com.pix.api.mapper;

import com.pix.api.dto.response.DeletePixKeyResponse;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeletePixKeyMapper {

    DeletePixKeyMapper INSTANCE = Mappers.getMapper(DeletePixKeyMapper.class);

    DeletePixKeyResponse toResponse(PixKey pixKey);

}
