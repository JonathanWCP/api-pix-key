package com.pix.api;

import com.pix.api.dto.ResponseJSONMessage;
import org.springframework.http.ResponseEntity;

public interface ICommandExecutor<T> {

    ResponseEntity execute(T t) throws Exception;

}
