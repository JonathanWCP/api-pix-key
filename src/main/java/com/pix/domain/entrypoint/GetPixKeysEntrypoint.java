package com.pix.domain.entrypoint;

import com.pix.api.dto.request.GetPixKeysRequest;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api")
public interface GetPixKeysEntrypoint {

    @GetMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GetPixKeyResponse>> get(@RequestParam GetPixKeysRequest getPixKeysRequest)
            throws PixKeyAlreadyDisableException, PixKeyNotFoundException;
}
