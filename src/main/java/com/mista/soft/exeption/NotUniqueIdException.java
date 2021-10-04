package com.mista.soft.exeption;

import com.mista.soft.domain.bouquet.Bouquet;

public class NotUniqueIdException extends Exception{
    public NotUniqueIdException(Bouquet bouquet) {
        super("Bouquet is not saved: " + bouquet);
    }
}
