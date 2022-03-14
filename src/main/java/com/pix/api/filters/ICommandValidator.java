package com.pix.api.filters;

public interface ICommandValidator<T> {
    void Validate(T t) throws Exception;
}
