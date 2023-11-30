package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String encriptar (String frase,String clave){
        //Array non-printable characters
        String[] nonPrint = {"NULL","STOH","STOT","ENOT","ENQY","ACKN","BELL","BACK","HORT","LIFE","VERT","FORM","CARR","SHIO","SHII","DATA","DEC1","DEC2","DEC3","DEC4","NEGA","SYNI","EOTB","CANC","ENDM","SUBS","ESCA","FILE","GRSE","RESE","UNSE","SPAC"};

        //Declarar variables para encriptar FRASE
        int l=frase.length();
        char[] arrayFraseChar= new char[l];
        int[] arrayFraseInt=new int[l];
        String[] arrayFraseString = new String [l];
        // Cambiar la frase de string a arreglo de caracteres
        arrayFraseChar = frase.toCharArray();
        System.out.println(arrayFraseChar);

        //declarar variables para encriptar CLAVE
        char[] arrayClaveChar= new char[l];
        int[] arrayClaveInt=new int[l];
        String[] arrayClaveString = new String [l];
        // Cambiar la frase de string a arreglo de caracteres
        //arrayClaveChar = clave.toCharArray();


        //  Para frase y clave:
        //  Recorrer cada caracter, obtener valor ASCII y después su valor en número binario
        // (terminamos con un arreglo de strings del codigo binario de cada letra)
        for (int i=0; i<l; i++){
            //FRASE
            arrayFraseInt[i] = arrayFraseChar[i]; //Obtener valor ASCII
            arrayFraseInt[i]= Integer.parseInt(Integer.toBinaryString(arrayFraseInt[i])); //Volver binario

            arrayFraseString[i]= Integer.toString(arrayFraseInt[i]); //Guardar como array de strings


            //CLAVE
            arrayClaveInt[i] = arrayClaveChar[i]; //Obtener valor ASCII
            arrayClaveInt[i]= Integer.parseInt(Integer.toBinaryString(arrayClaveInt[i])); //Volver binario

            arrayClaveString[i]= Integer.toString(arrayClaveInt[i]); //Guardar como array de strings

        }

        //USAR XOR
        System.out.println(Arrays.toString(arrayFraseInt));
        System.out.println(Arrays.toString(arrayClaveInt));

        int tamanioBloque = arrayFraseString[0].length();
        System.out.println(tamanioBloque);
        String[] arrayXOR= new String[l];
        int[] arrayXORInt = new int[l];
        String[] arrayXORString = new String[l];
        int numero = 0;

        // ciclo for que recorre cada digito de frase y clave para compararlos
        for (int bloque = 0; bloque<l; bloque++){
            for (int digito=0; digito<tamanioBloque;digito++){
                //Comparar digitos de frase y clave y hacer XOR
                if(arrayClaveString[bloque].charAt(digito)==arrayFraseString[bloque].charAt(digito)) { //Si los digitos son iguales, el valor en la tabla XOR es "0"
                    if(arrayXOR[bloque]==null){
                        arrayXOR[bloque] ="0";
                    }else{
                        arrayXOR[bloque] +='0';
                    }

                }else{ // si los digitos son diferentes, el valor en la tabla XOR es "1"
                    if(arrayXOR[bloque]==null){
                        arrayXOR[bloque] ="1";
                    }else{
                        arrayXOR[bloque] +='1';
                    }

                }



            }
            arrayXORInt[bloque]=Integer.parseInt(arrayXOR[bloque],2); //pasarlo de binario a decimal


        }
        System.out.println(Arrays.toString(arrayXORInt));
        //arrayXORInt= new int[]{40, 50, 60, 70}; // caso de prueba
        // convertir el resultado XOR  de valor ASCII  a caracteres
        int posicion= 0;
        for(int n:arrayXORInt){
            if(n<=32){ //check non-printable characters
                arrayXORString[posicion]=nonPrint[n-1];
            }else{
                arrayXORString[posicion]= String.valueOf((char)n);

            }
            posicion++;

        }
        System.out.println(Arrays.toString(arrayXORString));


        //Quitar comas, backets y espacios
        String formattedString = Arrays.toString(arrayXORString)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .trim();


        return formattedString;


    }

    public static void main(String[] args) {
        int menu = 1;
        String frase;
        String clave;
        Scanner scan = new Scanner(System.in);

        while(menu != 0){


            System.out.println("Elija una opción del menú (ingrese el numero de la accion que desea realizar): ");
            System.out.println("0. Salir");
            System.out.println("1. Encriptar una frase" );
            System.out.println("2. Desencriptar una frase");
            menu = scan.nextInt();

            switch(menu){
                case 0:
                    break;
                case 1:
                    System.out.println("Escriba la frase que desea encriptar: ");
                    scan.nextLine();
                    frase = scan.nextLine();
                    System.out.println("Escriba la clave que desea utilizar (debe ser de la misma longitud que la frase)");
                    clave = scan.nextLine();
                    System.out.println(encriptar(frase,clave));
                    break;
                case 2:
                    System.out.println("Escriba la frase que desea desencriptar: ");
                    scan.nextLine();
                    frase = scan.nextLine();
                    System.out.println("Escriba la clave que desea utilizar (debe ser de la misma longitud que la frase)");
                    clave = scan.nextLine();
                    System.out.println(encriptar(frase,clave));
                    break;


                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        }




    }
}