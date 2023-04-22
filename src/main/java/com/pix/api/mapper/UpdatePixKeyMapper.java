package com.pix.api.mapper;

import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.dto.response.UpdatePixKeyResponse;
import com.pix.domain.models.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdatePixKeyMapper {

    UpdatePixKeyMapper INSTANCE = Mappers.getMapper(UpdatePixKeyMapper.class);

    PixKey UpdatePixKeyRequestToPixKey(UpdatePixKeyRequest updatePixKeyRequest);

    UpdatePixKeyResponse PixKeyToUpdatePixKeyResponse(PixKey pixKey);
}
