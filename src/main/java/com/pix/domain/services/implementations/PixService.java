package com.pix.domain.services.implementations;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.repositories.PixRepository;
import com.pix.domain.services.IPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixService implements IPixService {

    @Autowired
    private PixRepository repository;

    @Override
    public String CreatePixKey(CreatePixKeyDTO createPixKeyDTO) throws PixKeyAlreadyExistsException {
        Optional<Integer> keyValueExists = repository.findAllPixKeyByKeyValue(createPixKeyDTO.getKeyValue());

        if (keyValueExists.isPresent() && keyValueExists.get() > 0) {
            throw new PixKeyAlreadyExistsException("The given pix key already exists!");
        }
        else {
            PixKey pixKey =  buildPixKey(
                    createPixKeyDTO.getKeyType(),
                    createPixKeyDTO.getKeyValue(),
                    createPixKeyDTO.getAgencyNumber(),
                    createPixKeyDTO.getAccountType(),
                    createPixKeyDTO.getAccountNumber(),
                    createPixKeyDTO.getAccountHolderName(),
                    createPixKeyDTO.getAccountHolderLastName(),
                    createPixKeyDTO.getPersonType());

            repository.saveAndFlush(pixKey);
            return pixKey.getId();
        }
    }

    @Override
    public PixKey UpdatePixKey(UpdatePixKeyDTO updatePixKeyDTO) throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        Optional<PixKey> pixKeyEntity = repository.findById(updatePixKeyDTO.getId());

        if (pixKeyEntity.isPresent()) {
            if (pixKeyEntity.get().getDatetimeInactivation() == null) {
                pixKeyEntity.get().setAccountType(updatePixKeyDTO.getAccountType());
                pixKeyEntity.get().setAgencyNumber(updatePixKeyDTO.getAgencyNumber());
                pixKeyEntity.get().setAccountNumber(updatePixKeyDTO.getAccountNumber());
                pixKeyEntity.get().setAccountHolderName(updatePixKeyDTO.getAccountHolderName());
                pixKeyEntity.get().setAccountHolderLastName(updatePixKeyDTO.getAccountHolderLastName());

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
                throw new PixKeyAlreadyDisableException("The given pix key is already disable!");
            else
                return pixKey.get();
        else {
            throw new PixKeyNotFoundException("The given pix key was not found!");
        }
    }

    @Override
    public List<PixKey> GetPixKeys(GetPixKeyDTO getPixKeyDTO) throws PixKeyNotFoundException {
        List<PixKey> pixKeys = repository.findAllPixKey(
                getPixKeyDTO.getKeyType(),
                getPixKeyDTO.getAgencyNumber(),
                getPixKeyDTO.getAccountNumber(),
                getPixKeyDTO.getAccountHolderName(),
                getPixKeyDTO.getDatetimeInclusion(),
                getPixKeyDTO.getDatetimeInactivation());

        if (pixKeys.size() == 0) {
            throw new PixKeyNotFoundException("The given pix key was not found!");
        }

        return pixKeys;
    }

    private PixKey buildPixKey(
            String keyType,
            String keyValue,
            int agencyNumber,
            String accountType,
            int accountNumber,
            String accountHolderName,
            String accountHolderLastName,
            String personType
    ) {
        PixKey pixKey = new PixKey();

        pixKey.setId(UUID.randomUUID().toString());
        pixKey.setKeyType(keyType);
        pixKey.setKeyValue(keyValue);
        pixKey.setAgencyNumber(agencyNumber);
        pixKey.setAccountType(accountType);
        pixKey.setAccountNumber(accountNumber);
        pixKey.setAccountHolderName(accountHolderName);
        pixKey.setAccountHolderLastName(accountHolderLastName);
        pixKey.setDatetimeInclusion(new Date(System.currentTimeMillis()));
        pixKey.setPersonType(personType);
        return pixKey;
    }
}
