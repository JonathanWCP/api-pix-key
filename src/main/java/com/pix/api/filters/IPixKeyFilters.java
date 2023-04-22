package com.pix.api.filters;

import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.api.dto.request.GetPixKeyRequest;

import java.math.BigDecimal;
import java.sql.Date;

public interface IPixKeyFilters {
    void CreatePixKeyValidator(CreatePixKeyRequest createPixKeyRequest);
    void DeletePixKeyValidator(String id);
    void GetPixKeyValidator(GetPixKeyRequest getPixKeyRequest);
    void GetPixKeysFilteredValidator(String keyType, BigDecimal agencyNumber, BigDecimal accountNumber, String accountHolderName, Date datetimeInclusion, Date datetimeInactivation);
    void UpdatePixKeyValidator(UpdatePixKeyRequest updatePixKeyRequest);
}
