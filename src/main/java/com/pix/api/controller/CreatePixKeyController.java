package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.api.filters.CreatePixKeyFilter;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.api.dto.ResponseJSONMessage;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CreatePixKeyController implements ICommandExecutor<CreatePixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Autowired
    private CreatePixKeyFilter createPixKeyFilter;

    @Override
    @PostMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> execute(@RequestBody CreatePixKeyDTO createPixKeyDTO) throws Exception {
        createPixKeyFilter.Validate(createPixKeyDTO);

        PixKey pixKey = buildPixKey(createPixKeyDTO);

        String createdPixKey = pixService.CreatePixKey(pixKey);

        return new ResponseEntity<>(createdPixKey, HttpStatus.CREATED);
    }

    private PixKey buildPixKey(CreatePixKeyDTO createPixKeyDTO) {
        PixKey pixKey = new PixKey();
        pixKey.setId(UUID.randomUUID().toString());
        pixKey.setKeyType(createPixKeyDTO.getKeyType().getValue());
        pixKey.setKeyValue(createPixKeyDTO.getKeyValue());
        pixKey.setAgencyNumber(createPixKeyDTO.getAgencyNumber());
        pixKey.setAccountType(createPixKeyDTO.getAccountType());
        pixKey.setAccountNumber(createPixKeyDTO.getAccountNumber());
        pixKey.setAccountHolderName(createPixKeyDTO.getAccountHolderName());
        pixKey.setAccountHolderLastName(createPixKeyDTO.getAccountHolderLastName());
        pixKey.setPersonType(createPixKeyDTO.getPersonType());
        pixKey.setDatetimeInclusion(new Date(System.currentTimeMillis()));

        return pixKey;
    }
}
