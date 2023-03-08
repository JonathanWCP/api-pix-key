package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.mapper.GetPixKeyMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetPixKeysController implements ICommandExecutor<GetPixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Override
    @GetMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PixKey>> execute(@RequestBody GetPixKeyDTO getPixKeyDTO) throws Exception {
        PixKey pixKey = GetPixKeyMapper.INSTANCE.GetPixKeyDtoToPixKey(getPixKeyDTO);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
