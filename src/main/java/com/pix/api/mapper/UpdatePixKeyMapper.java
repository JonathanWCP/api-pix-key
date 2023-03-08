package com.pix.api.mapper;

import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdatePixKeyMapper {

    UpdatePixKeyMapper INSTANCE = Mappers.getMapper(UpdatePixKeyMapper.class);

    PixKey UpdatePixKeyDtoToPixKey(UpdatePixKeyDTO updatePixKeyDTO);
}
