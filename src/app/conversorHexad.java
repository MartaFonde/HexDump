package app;

import java.io.*;
import java.util.Scanner;

public class conversorHexad {
    public static void leerHex(String hexa) {
        boolean nuevaFila = true;
        int hexFila = 0;
        String loQueQueda = "";
        String fila;
        String filaFormat = "";
        int ceros;

        for (int i = 1; i <= hexa.length(); i++) {
            if (nuevaFila) {
                ceros = 7 - (Integer.toHexString(hexFila).length());

                if (Integer.toHexString(hexFila).length() == 2) {
                    ceros++;
                }

                fila = String.format("%" + ceros + "s", Integer.toHexString(hexFila));
                filaFormat = fila.replace(' ', '0');
                System.out.printf("%s%d  ", filaFormat, 0);
                hexFila++;
            }

            nuevaFila = false;
            System.out.print(hexa.charAt(i - 1));

            if (i % 4 == 0) {
                System.out.print(" ");
            }
            if (i % 32 == 0) {
                System.out.println();
                nuevaFila = true;
            }

            if (nuevaFila && hexa.substring(i, hexa.length()).length() < 32) {
                loQueQueda = hexa.substring(i, hexa.length());
            }
        }

        System.out.println();
        System.out.printf("%s%s", filaFormat, (Integer.toHexString((loQueQueda.length()) / 2)));
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            File archivo = new File(args[0]);
            String texto;
            String converChar;
            String hexa = "";
            if (args.length == 1) {
                if (archivo.exists()) {
                    try (Scanner f = new Scanner(archivo)) {
                        while (f.hasNext()) {
                            texto = f.nextLine();
                            for (int i = 0; i < texto.length(); i++) {
                                converChar = Integer.toHexString(texto.charAt(i));
                                if (converChar.length() == 1) {
                                    converChar = "0" + converChar;
                                }
                                hexa = hexa.concat(converChar);
                            }
                            hexa = hexa.concat("0" + Integer.toHexString('\n'));
                        }
                        leerHex(hexa);
                        System.out.println();

                    } catch (IOException e) {
                        System.out.println("Error de lectura: " + e.getMessage());
                    }
                }
            }

        }

    }
}