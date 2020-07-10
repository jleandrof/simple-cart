package com.example.simpleecommerce.utils.results;

import lombok.Getter;

@Getter
public class ErrorResult extends AbstractResult {
    private final String message;

    public ErrorResult(String message) {
        super("error", ResultCodes.INTERNAL_ERROR);
        this.message = message;
    }

    public ErrorResult(String message, Long code) {
        super("error", code);
        this.message = message;
    }

    public ErrorResult() {
        super("error", ResultCodes.INTERNAL_ERROR);
        this.message = "Erro interno";
    }
}
