package com.genome.dx.core.model.error;

import com.genome.dx.core.model.msg.Msg;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Error<T> extends Msg<T> {
    List<Error> errors;

    public Error(String code) {
        this(code, null);
    }

    public Error(String code, String message) {
        this(code, message, null, null);
    }

    public Error(String code, String message, List<Error> errors, T data) {
        super(code, message, data);
        this.errors = errors;
    }

    public void addError(Error e) {
        if (null == errors) {
            errors = new ArrayList<>();
        }
        errors.add(e);
    }
}
