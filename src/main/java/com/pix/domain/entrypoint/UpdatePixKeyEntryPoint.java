package com.pix.domain.entrypoint;

import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.dto.response.UpdatePixKeyResponse;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface UpdatePixKeyEntryPoint {

    @PatchMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UpdatePixKeyResponse> update(@RequestBody UpdatePixKeyRequest updatePixKeyRequest)
            throws PixKeyAlreadyDisableException, PixKeyNotFoundException;
}
