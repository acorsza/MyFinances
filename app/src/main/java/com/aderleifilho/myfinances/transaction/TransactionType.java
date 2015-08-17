package com.aderleifilho.myfinances.transaction;

import java.util.Date;

/**
 * Created by aderleifilho on 8/13/15.
 *
 * Interface for Transaction Types
 *
 */
public interface TransactionType {

    double calculate(double value, Date date);

}
