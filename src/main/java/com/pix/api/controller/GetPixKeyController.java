package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.api.mapper.GetPixKeyMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetPixKeyController implements ICommandExecutor<String> {

    @Autowired
    private IPixService pixService;

    @GetMapping(path = "/pix/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetPixKeyResponse> execute(@PathVariable String id) throws Exception {
        PixKey pixKey = pixService.GetPixKey(id);

        GetPixKeyResponse getPixKeyResponse = GetPixKeyMapper.INSTANCE.PixKeyToGetPixKeyResponse(pixKey);

        return new ResponseEntity<>(getPixKeyResponse, HttpStatus.OK);
    }
}
