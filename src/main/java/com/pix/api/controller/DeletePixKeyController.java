package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeletePixKeyController implements ICommandExecutor<String> {

    @Autowired
    private IPixService pixService;

    @DeleteMapping(path = "/pix/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PixKey> execute(@PathVariable String id) throws Exception {
        PixKey removedPixKey = pixService.InactivatePixKey(id);

        return new ResponseEntity<>(removedPixKey, HttpStatus.OK);
    }
}
