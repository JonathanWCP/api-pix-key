package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.api.dto.response.CreatePixKeyResponse;
import com.pix.api.filters.IPixKeyFilters;
import com.pix.api.mapper.CreatePixKeyMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreatePixKeyController implements ICommandExecutor<CreatePixKeyRequest> {

    @Autowired
    private IPixService pixService;

    @Autowired
    private IPixKeyFilters validator;

    @PostMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePixKeyResponse> execute(@RequestBody CreatePixKeyRequest createPixKeyRequest) throws Exception {
        validator.CreatePixKeyValidator(createPixKeyRequest);

        final PixKey pixKey = CreatePixKeyMapper.INSTANCE.createPixKeyRequestToPixKey(createPixKeyRequest);

        final String createdPixKeyId = pixService.CreatePixKey(pixKey);

        final CreatePixKeyResponse createPixKeyResponse = new CreatePixKeyResponse(createdPixKeyId);

        return new ResponseEntity<>(createPixKeyResponse, HttpStatus.CREATED);
    }
}
