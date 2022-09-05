package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetPixKeysController implements ICommandExecutor<GetPixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Override
    @GetMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PixKey>> execute(GetPixKeyDTO getPixKeyDTO) throws Exception {
        PixKey pixKey = buildPixKey(getPixKeyDTO);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PixKey buildPixKey(GetPixKeyDTO getPixKeyDTO) {
        PixKey pixKey = new PixKey();

        pixKey.setKeyType(getPixKeyDTO.getKeyType());
        pixKey.setAgencyNumber(getPixKeyDTO.getAgencyNumber());
        pixKey.setAccountNumber(getPixKeyDTO.getAccountNumber());
        pixKey.setAccountHolderName(getPixKeyDTO.getAccountHolderName());
        pixKey.setDatetimeInclusion(getPixKeyDTO.getDatetimeInclusion());
        pixKey.setDatetimeInactivation(getPixKeyDTO.getDatetimeInactivation());

        return pixKey;
    }
}
