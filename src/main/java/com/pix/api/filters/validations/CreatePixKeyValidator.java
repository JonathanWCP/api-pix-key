package com.pix.api.filters.validations;

import br.com.fluentvalidator.AbstractValidator;
import com.pix.api.dto.request.CreatePixKeyRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

@Component
public class CreatePixKeyValidator extends AbstractValidator<CreatePixKeyRequest> {

    private static final String STRING_EMPTY_OR_NULL_MESSAGE = "The field should not be empty or null!";

    @Override
    public void rules() {

        setPropertyOnContext("createPixKeyDTO");

        Collection<String> acceptedKeyTypes = new ArrayList<>();
        acceptedKeyTypes.add("celular");
        acceptedKeyTypes.add("email");
        acceptedKeyTypes.add("cpf");
        acceptedKeyTypes.add("cnpj");
        acceptedKeyTypes.add("aleatorio");

        ruleFor("keyType" , CreatePixKeyRequest::getKeyType)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor("keyValue" , CreatePixKeyRequest::getKeyValue)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor("accountType" , CreatePixKeyRequest::getAccountType)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

//        ruleFor("keyType" ,CreatePixKeyDTO::get)
//                .must(not(stringEmptyOrNull()))
//                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor(CreatePixKeyRequest::getKeyType)
                .must(stringInCollection(acceptedKeyTypes))
                .withMessage("Invalid key type! It should be celular, email, cpf, cnpj or aleatorio!")
                .withFieldName("keyType")
                .critical();

//        ruleFor(CreatePixKeyDTO::getKeyValue)
//            .must(isNumber())
//                .when(stringEquals())

    }
}
