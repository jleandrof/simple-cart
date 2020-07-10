package com.example.simpleecommerce.utils.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractResult {
    private final String status;
    private final Long code;
}
