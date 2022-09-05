package com.pix.api.controller;

import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
public class GetPixKeysFilteredController {

    @Autowired
    private IPixService pixService;

    @GetMapping(path = "/pixv2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PixKey>> execute(
            @RequestParam(name = "keyType") String keyType,
            @RequestParam(name = "agencyNumber", required = false) BigDecimal agencyNumber,
            @RequestParam(name = "accountNumber", required = false) BigDecimal accountNumber,
            @RequestParam(name = "accountHolderName", required = false) String accountHolderName,
            @RequestParam(name = "datetimeInclusion", required = false) Date datetimeInclusion,
            @RequestParam(name = "datetimeInactivation", required = false) Date datetimeInactivation) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        PixKey pixKey = buildPixKey(keyType, agencyNumber, accountNumber, accountHolderName, datetimeInclusion, datetimeInactivation);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    private PixKey buildPixKey(
            String keyType,
            BigDecimal agencyNumber,
            BigDecimal accountNumber,
            String accountHolderName,
            Date datetimeInclusion,
            Date datetimeInactivation
    ) {
        PixKey pixKey = new PixKey();
        pixKey.setKeyType(keyType);
        pixKey.setAgencyNumber(agencyNumber);
        pixKey.setAccountNumber(accountNumber);
        pixKey.setAccountHolderName(accountHolderName);
        pixKey.setDatetimeInclusion(datetimeInclusion);
        pixKey.setDatetimeInactivation(datetimeInactivation);
        return pixKey;
    }

}
