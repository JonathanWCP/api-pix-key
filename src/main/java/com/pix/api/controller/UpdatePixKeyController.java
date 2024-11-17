package com.pix.api.controller;

import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.dto.response.UpdatePixKeyResponse;
import com.pix.api.mapper.UpdatePixKeyMapper;
import com.pix.domain.entrypoint.UpdatePixKeyEntryPoint;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePixKeyController implements UpdatePixKeyEntryPoint {

    @Autowired
    private PixService pixService;

    @Override
    public ResponseEntity<UpdatePixKeyResponse> update(UpdatePixKeyRequest updatePixKeyRequest) throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        PixKey pixKey = UpdatePixKeyMapper.INSTANCE.UpdatePixKeyRequestToPixKey(updatePixKeyRequest);

        var updatedPixKey = pixService.UpdatePixKey(pixKey);

        return new ResponseEntity<>(updatedPixKey, HttpStatus.OK);
    }
}
