package com.mx.modyo.utils;

public class ApiUtils {
	
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Long.valueOf(cadena.trim());
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
