package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.GetPixKeysFilteredDTO;
import com.pix.api.mapper.GetPixKeyFilteredMapper;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetPixKeysFilteredController implements ICommandExecutor<GetPixKeysFilteredDTO>{

    @Autowired
    private IPixService pixService;

    @GetMapping(path = "/pixv2", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity execute(@RequestBody GetPixKeysFilteredDTO getPixKeysFilteredDTO) throws Exception {
        PixKey pixKey = GetPixKeyFilteredMapper.INSTANCE.getPixKeyFilteredToPixKey(getPixKeysFilteredDTO);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
