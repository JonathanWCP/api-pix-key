package com.pix.api.mapper;

import com.pix.api.dto.GetPixKeysFilteredDTO;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetPixKeyFilteredMapper {

    GetPixKeyFilteredMapper INSTANCE = Mappers.getMapper(GetPixKeyFilteredMapper.class);

    @Mapping(source = "keyType", target = "keyType")
    @Mapping(source = "agencyNumber", target = "agencyNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "accountHolderName", target = "accountHolderName")
    @Mapping(source = "accountHolderLastName", target = "accountHolderLastName")
    @Mapping(source = "datetimeInclusion", target = "datetimeInclusion")
    @Mapping(source = "datetimeInactivation", target = "datetimeInactivation")
    PixKey getPixKeyFilteredToPixKey(GetPixKeysFilteredDTO getPixKeysFilteredDTO);

}
