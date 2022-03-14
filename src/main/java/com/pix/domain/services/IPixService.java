package com.pix.domain.services;

import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import java.util.List;

public interface IPixService {
    String CreatePixKey(PixKey pixKey) throws PixKeyAlreadyExistsException, PixKeyLimitReachedException;
    PixKey UpdatePixKey(PixKey pixKey) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    PixKey InactivatePixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    PixKey GetPixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
    List<PixKey> GetPixKeys(PixKey pixKey) throws PixKeyNotFoundException, PixKeyAlreadyDisableException;
}
