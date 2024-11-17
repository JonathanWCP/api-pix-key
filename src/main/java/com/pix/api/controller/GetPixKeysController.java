package com.pix.api.controller;

import com.pix.api.dto.request.GetPixKeysRequest;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.api.mapper.GetPixKeysMapper;
import com.pix.domain.entrypoint.GetPixKeysEntrypoint;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetPixKeysController implements GetPixKeysEntrypoint {

    @Autowired
    private PixService pixService;

    @Override
    public ResponseEntity<List<GetPixKeyResponse>> get(GetPixKeysRequest getPixKeysRequest) throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        PixKey pixKey = GetPixKeysMapper.INSTANCE.getPixKeyFilteredToPixKey(getPixKeysRequest);

        var pixKeyResponsesonse = pixService.GetPixKeys(pixKey);

        return new ResponseEntity<>(pixKeyResponsesonse, HttpStatus.OK);
    }
}
