package com.example.simpleecommerce.utils.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfullResult extends AbstractResult {
    private Object result;

    public SuccessfullResult(Object result) {
        super("ok", ResultCodes.OPERATION_SUCCESSFULL);
        this.result = result;
    }
}
