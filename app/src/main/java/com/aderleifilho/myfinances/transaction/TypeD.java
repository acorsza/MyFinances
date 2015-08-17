package com.aderleifilho.myfinances.transaction;

import android.util.Log;

import java.util.Date;

/**
 * Created by aderleifilho on 8/13/15.
 *
 * This class is used to deal with transactions type D
 *
 */
public class TypeD implements TransactionType {

    private static final String TAG = "MyFinances";

    public double calculate (double value, Date date) {

        Log.i(TAG, "TypeD :: calculate(double " + value + ", Date " + date);

        /**
         * Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da transferência.
         *  Valores até $25.000 seguem a taxação tipo A
         *  Valores de $25.001 até $120.000 seguem a taxação tipo B
         *  Valores maiores que $120.000 seguem a taxação tipo C
         */

        double newValue = 0.0;


        if (value > 0 && value <= 25000) {
            TypeA tipoA = new TypeA();
            newValue = tipoA.calculate(value, date);
        } else if (value > 25000 && value <= 120000) {
            TypeB tipoB = new TypeB();
            newValue = tipoB.calculate(value, date);
        } else if (value > 120000) {
            TypeC tipoC = new TypeC();
            newValue = tipoC.calculate(value, date);
        }

        return newValue;
    }
}
