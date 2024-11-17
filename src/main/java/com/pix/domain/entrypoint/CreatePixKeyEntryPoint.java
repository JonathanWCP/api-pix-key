package com.pix.domain.entrypoint;

import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.api.dto.response.CreatePixKeyResponse;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface CreatePixKeyEntryPoint {

    @PostMapping(value = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreatePixKeyResponse> create(@RequestBody CreatePixKeyRequest createPixKeyRequest)
            throws PixKeyLimitReachedException, PixKeyAlreadyExistsException;
}
