package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.request.GetPixKeysFilteredRequest;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.api.mapper.GetPixKeyFilteredMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetPixKeysFilteredController implements ICommandExecutor<GetPixKeysFilteredRequest> {

    @Autowired
    private IPixService pixService;


    @GetMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetPixKeyResponse>> execute(GetPixKeysFilteredRequest getPixKeysFilteredRequest) throws Exception {
        PixKey pixKey = GetPixKeyFilteredMapper.INSTANCE.getPixKeyFilteredToPixKey(getPixKeysFilteredRequest);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        List<GetPixKeyResponse> getPixKeyResponses = GetPixKeyFilteredMapper.INSTANCE.MapPixKeyListToGetPixKeyResponseList(response);

        return new ResponseEntity<>(getPixKeyResponses, HttpStatus.OK);

    }
}
