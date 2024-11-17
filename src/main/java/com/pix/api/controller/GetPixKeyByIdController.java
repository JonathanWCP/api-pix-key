package com.pix.api.controller;

import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.domain.entrypoint.GetPixKeyByIdEntryPoint;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPixKeyByIdController implements GetPixKeyByIdEntryPoint {

    @Autowired
    private PixService pixService;

    @Override
    public ResponseEntity<GetPixKeyResponse> create(String id) throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        var pixKeyResponse = pixService.GetPixKey(id);

        return new ResponseEntity<>(pixKeyResponse, HttpStatus.OK);
    }
}
