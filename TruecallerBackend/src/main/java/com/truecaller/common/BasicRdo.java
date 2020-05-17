package com.truecaller.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
/***
 * Basic Response Object for APIs
 *
 */
public class BasicRdo<T> {


    public boolean status;

    public String message;

    public T data;

    public BasicRdo(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public BasicRdo() {

    }

    public ResponseEntity<BasicRdo> getResponse(String message, HttpStatus responseStatus, T object) {

        BasicRdo<T> basicRdo = new BasicRdo();
        basicRdo.message = message;

        if (object == null) {
            basicRdo.status = false;
        } else {
            basicRdo.status = true;
            basicRdo.data = object;
        }

        return new ResponseEntity<BasicRdo>(basicRdo, responseStatus);

    }

    public ResponseEntity<BasicRdo> getResponse(String message, HttpStatus responseStatus, T object, boolean status) {

        BasicRdo<T> basicRdo = new BasicRdo();
        basicRdo.message = message;
        basicRdo.status = status;

        if (object != null) {
            basicRdo.data = object;
        }

        return new ResponseEntity<BasicRdo>(basicRdo, responseStatus);

    }


}
