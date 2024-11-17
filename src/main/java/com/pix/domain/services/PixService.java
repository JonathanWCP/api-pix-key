package com.pix.domain.services;

import com.pix.api.dto.response.DeletePixKeyResponse;
import com.pix.api.dto.response.GetPixKeyResponse;
import com.pix.api.dto.response.UpdatePixKeyResponse;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import java.util.List;

public interface PixService {
    String CreatePixKey(PixKey pixKey) throws PixKeyAlreadyExistsException, PixKeyLimitReachedException;
    UpdatePixKeyResponse UpdatePixKey(PixKey pixKey) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    DeletePixKeyResponse InactivatePixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    GetPixKeyResponse GetPixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    List<GetPixKeyResponse> GetPixKeys(PixKey pixKey) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
}
