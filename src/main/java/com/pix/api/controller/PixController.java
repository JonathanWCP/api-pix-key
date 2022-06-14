package com.pix.api.controller;

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
public class PixController {

    @Configuration
    static class PixControllerContextConfiguration {
        @Bean
        public ResponseJSONMessage createJsonResponse() {
            return new ResponseJSONMessage();
        }
    }

    @Autowired
    private IPixService pixService;

    @Autowired
    private CreatePixKeyFilter createPixKeyFilter;

    @Autowired
    private ResponseJSONMessage response;


    @GetMapping(path="/healthcheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PostMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseJSONMessage CreatePixKey(@RequestBody CreatePixKeyDTO createPixKeyDTO) throws Exception {
        createPixKeyFilter.Validate(createPixKeyDTO);

        PixKey pixKey = buildPixKey(createPixKeyDTO);

        response.setId(pixService.CreatePixKey(pixKey));

        return response;
    }

    @PatchMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public PixKey UpdatePixKey(@RequestBody UpdatePixKeyDTO updatePixKeyDTO) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        PixKey pixKey = buildPixKey(updatePixKeyDTO);

        return pixService.UpdatePixKey(pixKey);
    }

    @DeleteMapping(path="/pix/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public PixKey DeletePixKey(@PathVariable("id") String id) throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        return pixService.InactivatePixKey(id);
    }

    @GetMapping(path="/pix/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetPixKeyById(@PathVariable String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {

        PixKey response = pixService.GetPixKey(id);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetPixKey(@RequestBody GetPixKeyDTO getPixKeyDTO) throws PixKeyNotFoundException, PixKeyAlreadyDisableException
    {
        PixKey pixKey = buildPixKey(getPixKeyDTO);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping(path="/pixv2", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetPixKeyV2(
            @RequestParam(name = "keyType") String keyType,
            @RequestParam(name = "agencyNumber", required = false) BigDecimal agencyNumber,
            @RequestParam(name = "accountNumber", required = false) BigDecimal accountNumber,
            @RequestParam(name = "accountHolderName", required = false) String accountHolderName,
            @RequestParam(name = "datetimeInclusion", required = false) Date datetimeInclusion,
            @RequestParam(name = "datetimeInactivation", required = false)  Date datetimeInactivation) throws PixKeyNotFoundException, PixKeyAlreadyDisableException
    {
        PixKey pixKey = buildPixKey(keyType, agencyNumber, accountNumber, accountHolderName, datetimeInclusion, datetimeInactivation);

        List<PixKey> response = pixService.GetPixKeys(pixKey);

        return new ResponseEntity(response, HttpStatus.OK);
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
