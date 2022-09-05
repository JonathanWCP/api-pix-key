package com.pix.api.controller;

import com.pix.api.ICommandExecutor;
import com.pix.api.dto.ResponseJSONMessage;
import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePixKeyController implements ICommandExecutor<UpdatePixKeyDTO> {

    @Autowired
    private IPixService pixService;

    @Override
    @PatchMapping(path = "/pix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PixKey> execute(UpdatePixKeyDTO updatePixKeyDTO) throws Exception {
        PixKey pixKey = buildPixKey(updatePixKeyDTO);

        PixKey updatedPixKey = pixService.UpdatePixKey(pixKey);

        return new ResponseEntity<>(updatedPixKey, HttpStatus.OK);
    }

    private PixKey buildPixKey(UpdatePixKeyDTO updatePixKeyDTO) {
        PixKey pixKey = new PixKey();

        pixKey.setId(updatePixKeyDTO.getId());
        pixKey.setAccountType(updatePixKeyDTO.getAccountType());
        pixKey.setAgencyNumber(updatePixKeyDTO.getAgencyNumber());
        pixKey.setAccountNumber(updatePixKeyDTO.getAccountNumber());
        pixKey.setAccountHolderName(updatePixKeyDTO.getAccountHolderName());
        pixKey.setAccountHolderLastName(updatePixKeyDTO.getAccountHolderLastName());

        return pixKey;
    }
}
