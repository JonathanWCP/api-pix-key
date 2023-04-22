package com.pix.api;

import org.springframework.http.ResponseEntity;

public interface ICommandExecutor<T> {

    ResponseEntity execute(T t) throws Exception;

}
