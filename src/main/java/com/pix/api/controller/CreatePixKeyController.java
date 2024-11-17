package com.pix.api.controller;

import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.api.dto.response.CreatePixKeyResponse;
import com.pix.api.filters.PixKeyFilters;
import com.pix.api.mapper.CreatePixKeyMapper;
import com.pix.domain.entrypoint.CreatePixKeyEntryPoint;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePixKeyController implements CreatePixKeyEntryPoint {

    @Autowired
    private PixService pixService;

    @Autowired
    private PixKeyFilters validator;

    @Override
    public ResponseEntity<CreatePixKeyResponse> create(CreatePixKeyRequest createPixKeyRequest) throws PixKeyLimitReachedException, PixKeyAlreadyExistsException {
        validator.CreatePixKeyValidator(createPixKeyRequest);

        final PixKey pixKey = CreatePixKeyMapper.INSTANCE.createPixKeyRequestToPixKey(createPixKeyRequest);

        final String createdPixKeyId = pixService.CreatePixKey(pixKey);

        final CreatePixKeyResponse createPixKeyResponse = new CreatePixKeyResponse(createdPixKeyId);

        return new ResponseEntity<>(createPixKeyResponse, HttpStatus.CREATED);
    }
}
