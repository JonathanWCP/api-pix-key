package com.pix.api.mapper;

import com.pix.api.dto.GetPixKeyDTO;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetPixKeyMapper {

    GetPixKeyMapper INSTANCE = Mappers.getMapper(GetPixKeyMapper.class);

    @Mapping(source = "keyType", target = "keyType")
    @Mapping(source = "accountHolderName", target = "accountHolderName")
    PixKey GetPixKeyDtoToPixKey(GetPixKeyDTO getPixKeyDTO);

}
