package com.pix.api.controller;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.api.filters.CreatePixKeyFilter;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PixController {

    @Autowired
    private IPixService pixService;

    @Autowired
    private CreatePixKeyFilter createPixKeyFilter;


    @GetMapping(path="/healthcheck", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PostMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public String CreatePixKey(@Valid @RequestBody CreatePixKeyDTO createPixKeyDTO) throws Exception {

        createPixKeyFilter.Validate(createPixKeyDTO);

        return pixService.CreatePixKey(createPixKeyDTO);
    }

    @PatchMapping(path="/pix", produces=MediaType.APPLICATION_JSON_VALUE)
    public PixKey UpdatePixKey(@RequestBody UpdatePixKeyDTO body) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {

        return pixService.UpdatePixKey(body);
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

    @GetMapping(
            path="/pix",
    produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetPixKey(
            @RequestParam(name = "keyType") String keyType,
            @RequestParam(name = "agencyNumber", required = false) BigDecimal agencyNumber,
            @RequestParam(name = "accountNumber", required = false) BigDecimal accountNumber,
            @RequestParam(name = "accountHolderName", required = false) String accountHolderName,
            @RequestParam(name = "datetimeInclusion", required = false) Date datetimeInclusion,
            @RequestParam(name = "datetimeInactivation", required = false)  Date datetimeInactivation) throws PixKeyNotFoundException, PixKeyAlreadyDisableException
    {
        GetPixKeyDTO getPixKeyDTO = buildGetPixKeyDTO(keyType, agencyNumber, accountNumber, accountHolderName, datetimeInclusion, datetimeInactivation);

        List<PixKey> response = pixService.GetPixKeys(getPixKeyDTO);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    private GetPixKeyDTO buildGetPixKeyDTO(
            String keyType,
            BigDecimal agencyNumber,
            BigDecimal accountNumber,
            String accountHolderName,
            Date datetimeInclusion,
            Date datetimeInactivation
    ) {
        GetPixKeyDTO getPixKeyDTO = new GetPixKeyDTO();
        getPixKeyDTO.setKeyType(keyType);
        getPixKeyDTO.setAgencyNumber(agencyNumber);
        getPixKeyDTO.setAccountNumber(accountNumber);
        getPixKeyDTO.setAccountHolderName(accountHolderName);
        getPixKeyDTO.setDatetimeInclusion(datetimeInclusion);
        getPixKeyDTO.setDatetimeInactivation(datetimeInactivation);
        return getPixKeyDTO;
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
