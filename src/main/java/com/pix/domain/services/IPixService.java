package com.pix.domain.services;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IPixService {
    String CreatePixKey(CreatePixKeyDTO createPixKeyDTO) throws PixKeyAlreadyExistsException;
    PixKey UpdatePixKey(UpdatePixKeyDTO updatePixKeyDTO) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    PixKey InactivatePixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    PixKey GetPixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    List<PixKey> GetPixKeys(GetPixKeyDTO getPixKeyDTO) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
}
