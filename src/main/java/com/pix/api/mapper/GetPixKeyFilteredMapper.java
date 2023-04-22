package com.pix.api.mapper;

import com.pix.api.dto.request.GetPixKeysFilteredRequest;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GetPixKeyFilteredMapper {

    GetPixKeyFilteredMapper INSTANCE = Mappers.getMapper(GetPixKeyFilteredMapper.class);

    @Mapping(target = "personType", ignore = true)
    @Mapping(target = "keyValue", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "accountHolderLastName", ignore = true)
    @Mapping(source = "keyType", target = "keyType")
    @Mapping(source = "agencyNumber", target = "agencyNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "accountHolderName", target = "accountHolderName")
    @Mapping(source = "datetimeInclusion", target = "datetimeInclusion")
    @Mapping(source = "datetimeInactivation", target = "datetimeInactivation")
    PixKey getPixKeyFilteredToPixKey(GetPixKeysFilteredRequest getPixKeysFilteredDTO);

    List<GetPixKeyResponse> MapPixKeyListToGetPixKeyResponseList(List<PixKey> pixKeys);

}
