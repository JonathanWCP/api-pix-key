package com.pix.api.filters.validations;

import br.com.fluentvalidator.AbstractValidator;
import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.enums.PixKeyType;

import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.*;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

public class CreatePixKeyValidator extends AbstractValidator<CreatePixKeyDTO> {
    @Override
    public void rules() {

//        ruleFor(CreatePixKeyDTO::getKeyType)
//                .must(not(nullValue()))
//                .withMessage("key type must be not null")
//                .withFieldName("keyType")
//                .critical();

        ruleFor(CreatePixKeyDTO::getKeyType)
                .must(instanceOf(String.class))
                .withMessage("key type must contains only characters!")
                .withFieldName("keyType")
                .critical();

    }
}
