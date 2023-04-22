package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.dto.response.UpdatePixKeyResponse;
import com.pix.api.mapper.UpdatePixKeyMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UpdatePixKeyController implements ICommandExecutor<UpdatePixKeyRequest> {

    @Autowired
    private IPixService pixService;

    @PatchMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatePixKeyResponse> execute(@RequestBody UpdatePixKeyRequest updatePixKeyRequest) throws Exception {
        PixKey pixKey = UpdatePixKeyMapper.INSTANCE.UpdatePixKeyRequestToPixKey(updatePixKeyRequest);

        PixKey updatedPixKey = pixService.UpdatePixKey(pixKey);

        UpdatePixKeyResponse updatePixKeyResponse = UpdatePixKeyMapper.INSTANCE.PixKeyToUpdatePixKeyResponse(updatedPixKey);

        return new ResponseEntity<>(updatePixKeyResponse, HttpStatus.OK);
    }
}
