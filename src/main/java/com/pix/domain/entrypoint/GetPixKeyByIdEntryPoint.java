package com.pix.domain.entrypoint;

import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface GetPixKeyByIdEntryPoint {

    @GetMapping(path = "/pix/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetPixKeyResponse> create(@PathVariable String id)
            throws PixKeyAlreadyDisableException, PixKeyNotFoundException;
}
