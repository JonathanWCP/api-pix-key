package com.pix.api.controller;

import com.pix.api.dto.response.DeletePixKeyResponse;
import com.pix.domain.entrypoint.DeletePixKeyEntrypoint;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeletePixKeyController implements DeletePixKeyEntrypoint {

    @Autowired
    private PixService pixService;

    @Override
    public ResponseEntity<DeletePixKeyResponse> delete(String id) throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        var removedPixKey = pixService.InactivatePixKey(id);

        return new ResponseEntity<>(removedPixKey, HttpStatus.OK);
    }
}
