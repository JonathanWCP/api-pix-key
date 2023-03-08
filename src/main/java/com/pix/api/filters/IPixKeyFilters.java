package com.pix.api.filters;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;

import java.math.BigDecimal;
import java.sql.Date;

public interface IPixKeyFilters {
    void CreatePixKeyValidator(CreatePixKeyDTO createPixKeyDTO);
    void DeletePixKeyValidator(String id);
    void GetPixKeyValidator(GetPixKeyDTO getPixKeyDTO);
    void GetPixKeysFilteredValidator(String keyType, BigDecimal agencyNumber, BigDecimal accountNumber, String accountHolderName, Date datetimeInclusion, Date datetimeInactivation);
    void UpdatePixKeyValidator(UpdatePixKeyDTO updatePixKeyDTO);
}
