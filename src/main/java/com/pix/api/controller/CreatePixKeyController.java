package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.CreatePixKeyDTO;
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
public class CreatePixKeyController implements ICommandExecutor<CreatePixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Autowired
    private IPixKeyFilters validator;

    @Override
    @PostMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> execute(@RequestBody CreatePixKeyDTO createPixKeyDTO) throws Exception {
        validator.CreatePixKeyValidator(createPixKeyDTO);

        PixKey pixKey = CreatePixKeyMapper.INSTANCE.createPixKeyDtoToPixKey(createPixKeyDTO);

        String createdPixKey = pixService.CreatePixKey(pixKey);

        return new ResponseEntity<>(createdPixKey, HttpStatus.CREATED);
    }
}
