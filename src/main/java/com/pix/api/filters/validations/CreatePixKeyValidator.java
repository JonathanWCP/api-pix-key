package com.pix.api.filters.validations;

import br.com.fluentvalidator.AbstractValidator;
import com.pix.api.dto.CreatePixKeyDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.ObjectPredicate.instanceOf;

@Component
public class CreatePixKeyValidator extends AbstractValidator<CreatePixKeyDTO> {
    @Override
    public void rules() {

        ruleFor(CreatePixKeyDTO::getKeyType)
            .must(instanceOf(String.class))
                .withMessage("key type must contains only characters!")
                .withFieldName("keyType")
                .critical();

    }
}
