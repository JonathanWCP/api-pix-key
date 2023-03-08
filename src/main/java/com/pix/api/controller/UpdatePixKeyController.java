package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.ResponseJSONMessage;
import com.pix.api.dto.UpdatePixKeyDTO;
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
public class UpdatePixKeyController implements ICommandExecutor<UpdatePixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Override
    @PatchMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PixKey> execute(@RequestBody UpdatePixKeyDTO updatePixKeyDTO) throws Exception {
        PixKey pixKey = UpdatePixKeyMapper.INSTANCE.UpdatePixKeyDtoToPixKey(updatePixKeyDTO);

        PixKey updatedPixKey = pixService.UpdatePixKey(pixKey);

        return new ResponseEntity<>(updatedPixKey, HttpStatus.OK);
    }
}
