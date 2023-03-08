package com.pix.api.filters.validations;

import br.com.fluentvalidator.AbstractValidator;
import com.pix.api.dto.CreatePixKeyDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

@Component
public class CreatePixKeyValidator extends AbstractValidator<CreatePixKeyDTO> {

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

        ruleFor("keyType" ,CreatePixKeyDTO::getKeyType)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor("keyValue" ,CreatePixKeyDTO::getKeyValue)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor("accountType" ,CreatePixKeyDTO::getAccountType)
                .must(not(stringEmptyOrNull()))
                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

//        ruleFor("keyType" ,CreatePixKeyDTO::get)
//                .must(not(stringEmptyOrNull()))
//                .withMessage(STRING_EMPTY_OR_NULL_MESSAGE);

        ruleFor(CreatePixKeyDTO::getKeyType)
                .must(stringInCollection(acceptedKeyTypes))
                .withMessage("Invalid key type! It should be celular, email, cpf, cnpj or aleatorio!")
                .withFieldName("keyType")
                .critical();

//        ruleFor(CreatePixKeyDTO::getKeyValue)
//            .must(isNumber())
//                .when(stringEquals())

    }
}
