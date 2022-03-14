package com.pix.domain.repositories;

import com.pix.domain.models.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PixRepository extends JpaRepository<PixKey, String> {

    @Query("SELECT count(u.id) FROM PixKey u WHERE u.keyValue = :keyValue")
    Optional<Integer> findAllPixKeyByKeyValue(@Param("keyValue") String keyValue);

    @Query("SELECT count(u.id) FROM PixKey u WHERE u.accountNumber = :accountNumber")
    Optional<Integer> getAmountOfPixKey(@Param("accountNumber") BigDecimal accountNumber);

    @Query("SELECT u FROM PixKey u WHERE " +
            "(:keyType is null or u.keyType = :keyType) AND " +
            "(:agencyNumber is null or u.agencyNumber = :agencyNumber) AND " +
            "(:accountNumber is null or u.accountNumber = :accountNumber) AND " +
            "(:accountHolderName is null or u.accountHolderName = :accountHolderName) AND " +
            "(:datetimeInclusion is null or u.datetimeInclusion = :datetimeInclusion) AND " +
            "(:datetimeInactivation is null or u.datetimeInactivation = :datetimeInactivation)")
    List<PixKey> findAllPixKey(
            @Param("keyType") String keyType,
            @Param("agencyNumber") BigDecimal agencyNumber,
            @Param("accountNumber") BigDecimal accountNumber,
            @Param("accountHolderName") String accountHolderName,
            @Param("datetimeInclusion") Date datetimeInclusion,
            @Param("datetimeInactivation") Date datetimeInactivation);

}
