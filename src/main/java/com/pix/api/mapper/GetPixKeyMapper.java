package com.pix.api.mapper;

import com.pix.api.dto.request.GetPixKeyRequest;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetPixKeyMapper {

    GetPixKeyMapper INSTANCE = Mappers.getMapper(GetPixKeyMapper.class);

    PixKey GetPixKeyRequestToPixKey(GetPixKeyRequest getPixKeyRequest);

    GetPixKeyResponse PixKeyToGetPixKeyResponse(PixKey pixKey);

}