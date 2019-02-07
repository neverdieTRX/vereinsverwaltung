package de.trx.veve.facade.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wird bei falschen Rest Parametern geworfen.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentRestException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public IllegalArgumentRestException() {
        super();
    }

    public IllegalArgumentRestException(String message) {
        super(message);
    }

}

