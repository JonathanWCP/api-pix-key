package com.pix.domain.services.implementations;

import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.repositories.PixRepository;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixService implements IPixService {

    @Autowired
    private PixRepository repository;

    @Override
    public String CreatePixKey(PixKey pixKey) throws PixKeyAlreadyExistsException, PixKeyLimitReachedException {
        final int maximumPixKeyForFisicaPersonType = 5;
        final int maximumPixKeyForJuridicaPersonType = 20;

        Optional<Integer> keyValueExists = repository.findAllPixKeyByKeyValue(pixKey.getKeyValue());
        Optional<Integer> amountOfPixKeyByAccountNumber = repository.getAmountOfPixKey(pixKey.getAccountNumber());

        if (keyValueExists.isPresent()) {
            if (keyValueExists.get() > 0) {
                throw new PixKeyAlreadyExistsException("The given pix key already exists!");
            }

            if (pixKey.getPersonType().equals("fisica")) {
                if (amountOfPixKeyByAccountNumber.get() >= maximumPixKeyForFisicaPersonType)
                    throw new PixKeyLimitReachedException("Pix key creation limit is 5 keys for 'fisica' person type");
            }
            else {
                if (amountOfPixKeyByAccountNumber.get() >= maximumPixKeyForJuridicaPersonType)
                    throw new PixKeyLimitReachedException("Pix key creation limit is 20 keys for 'juridica' person type");
            }
        }

        pixKey.setId(UUID.randomUUID().toString());

        repository.saveAndFlush(pixKey);
        return pixKey.getId();
    }

    @Override
    public PixKey UpdatePixKey(PixKey pixKey) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        Optional<PixKey> pixKeyEntity = repository.findById(pixKey.getId());

        if (pixKeyEntity.isPresent()) {
            if (pixKeyEntity.get().getDatetimeInactivation() == null) {
                pixKeyEntity.get().setAccountType(pixKey.getAccountType());
                pixKeyEntity.get().setAgencyNumber(pixKey.getAgencyNumber());
                pixKeyEntity.get().setAccountNumber(pixKey.getAccountNumber());
                pixKeyEntity.get().setAccountHolderName(pixKey.getAccountHolderName());
                pixKeyEntity.get().setAccountHolderLastName(pixKey.getAccountHolderLastName());

                repository.saveAndFlush(pixKeyEntity.get());
                return pixKeyEntity.get();
            }
            else {
                throw new PixKeyAlreadyDisableException("The given pix key is disable!");
            }
        }
        else {
            throw new PixKeyNotFoundException("The given pix key was not found!");
        }
    }

    @Override
    public PixKey InactivatePixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        Optional<PixKey> pixKey = repository.findById(id);

        if (pixKey.isPresent()) {
            if (pixKey.get().getDatetimeInactivation() != null) {
                throw new PixKeyAlreadyDisableException("The given pix key is disable!");
            }

            pixKey.get().setDatetimeInactivation(new Date(System.currentTimeMillis()));
            repository.saveAndFlush(pixKey.get());
            return pixKey.get();
        }
        else {
            throw new PixKeyNotFoundException("The given pix key was not found!");
        }
    }

    @Override
    public PixKey GetPixKey(String id) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        Optional<PixKey> pixKey = repository.findById(id);

        if(pixKey.isPresent())
            if (pixKey.get().getDatetimeInactivation() != null)
                throw new PixKeyAlreadyDisableException("Provided pix key is disabled!");
            else
                return pixKey.get();
        else {
            throw new PixKeyNotFoundException("The given pix key was not found!");
        }
    }

    @Override
    public List<PixKey> GetPixKeys(PixKey pixKey) throws PixKeyNotFoundException {
        List<PixKey> pixKeys = repository.findAllPixKey(
                pixKey.getKeyType(),
                pixKey.getAgencyNumber(),
                pixKey.getAccountNumber(),
                pixKey.getAccountHolderName(),
                pixKey.getDatetimeInclusion(),
                pixKey.getDatetimeInactivation());

        if (pixKeys.size() == 0)
            throw new PixKeyNotFoundException("The given pix key was not found!");

        return pixKeys;
    }
}
