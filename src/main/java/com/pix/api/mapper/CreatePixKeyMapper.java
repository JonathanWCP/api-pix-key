package com.pix.api.mapper;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreatePixKeyMapper {

    CreatePixKeyMapper INSTANCE = Mappers.getMapper(CreatePixKeyMapper.class);

    @Mapping(source = "keyType", target = "keyType")
    @Mapping(source = "keyValue", target = "keyValue")
    @Mapping(source = "agencyNumber", target = "agencyNumber")
    @Mapping(source = "accountType", target = "accountType")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "accountHolderName", target = "accountHolderName")
    @Mapping(source = "accountHolderLastName", target = "accountHolderLastName")
    @Mapping(source = "personType", target = "personType")
    PixKey createPixKeyDtoToPixKey(CreatePixKeyDTO createPixKeyDTO);

}
