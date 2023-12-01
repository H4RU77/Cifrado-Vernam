package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static String encriptar (String frase,String clave){
        //Array non-printable characters
        String[] nonPrint = {"NULL","STOH","STOT","ENTX","ENTR","ENQY","ACKN","BELL","BACK","HORT","LIFE","VERT","FORM","CARR","SHIO","SHII","DATA","DEC1","DEC2","DEC3","DEC4","NEGA","SYNI","EOTB","CANC","ENDM","SUBS","ESCA","FILE","GRSE","RESE","UNSE","SPAC"};

        //Volver frase y clave a minúsculas
        frase = frase.toLowerCase();
        clave = clave.toLowerCase();

        //Declarar variables para encriptar FRASE
        int l=frase.length();
        System.out.println(l);
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
        arrayClaveChar = clave.toCharArray();
        System.out.println(arrayClaveChar);


        //  Para frase y clave:
        //  Recorrer cada caracter, obtener valor ASCII y después su valor en número binario
        // (terminamos con un arreglo de strings del codigo binario de cada letra)
        for (int i=0; i<l; i++){
            //FRASE
            arrayFraseInt[i] = arrayFraseChar[i]; //Obtener valor ASCII

            arrayFraseInt[i]= Integer.parseInt(Integer.toBinaryString(arrayFraseInt[i])); //Volver binario

            arrayFraseString[i]= String.format("%7s", Integer.toString(arrayFraseInt[i])).replace(' ', '0'); //Guardar como array de strings


            //CLAVE
            arrayClaveInt[i] = arrayClaveChar[i]; //Obtener valor ASCII
            arrayClaveInt[i]= Integer.parseInt(Integer.toBinaryString(arrayClaveInt[i])); //Volver binario

            arrayClaveString[i]= String.format("%7s", Integer.toString(arrayClaveInt[i])).replace(' ', '0'); //Guardar como array de strings de longitud 7

        }

        //Revisar variables en binario
        System.out.println(Arrays.toString(arrayFraseString));
        System.out.println(Arrays.toString(arrayClaveString));


        //USAR XOR

        String[] arrayXOR= new String[l];
        int[] arrayXORInt = new int[l];
        String[] arrayXORString = new String[l];


        // ciclo for que recorre cada digito de frase y clave para compararlos
        for (int bloque = 0; bloque<l; bloque++){
            for (int digito=0; digito<7;digito++){
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

        // convertir el resultado XOR  de valor ASCII  a caracteres
        int posicion= 0;
        for(int n:arrayXORInt){

             if(n<=32){ //check non-printable characters
                arrayXORString[posicion]=nonPrint[n];
            } else if(n==127){
                 arrayXORString[posicion]=nonPrint[n]="DELE";
             }else{
                arrayXORString[posicion]= String.valueOf((char)n).toLowerCase(); // printable characters a minúscula

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

    public static String desencriptar (String frase,String clave){

        //Array non-printable characters
        String[] nonPrint = {"NULL","STOH","STOT","ENTX","ENTR","ENQY","ACKN","BELL","BACK","HORT","LIFE","VERT","FORM","CARR","SHIO","SHII","DATA","DEC1","DEC2","DEC3","DEC4","NEGA","SYNI","EOTB","CANC","ENDM","SUBS","ESCA","FILE","GRSE","RESE","UNSE","SPAC"};
        //Hash map para nonprintable characters
        HashMap<String, Integer> mapita = new HashMap<>();
        // Agregar elementos
        for (int i = 0; i < nonPrint.length; i++) {
            mapita.put(nonPrint[i], i);
        }



        //Declarar variables para encriptar FRASE
        int l=frase.length();
        System.out.println(l);


        char[] arrayFraseChar= new char[l];
        int[] arrayFraseInt=new int[clave.length()];
        String[] arrayFraseString = new String [l];
        // Cambiar la frase de string a arreglo de caracteres
        arrayFraseChar = frase.toCharArray();



        //declarar variables para encriptar CLAVE
        char[] arrayClaveChar= new char[l];
        int[] arrayClaveInt=new int[l];
        String[] arrayClaveString = new String [l];
        // Cambiar la clave de string a arreglo de caracteres
        arrayClaveChar = clave.toCharArray();

        int contador=0;
        // Cambiar la frase de arreglo de chars a arreglo de strings y después  a arreglo de ints
        for(int i=0; i<l;i++){
            if(Character.isUpperCase(arrayFraseChar[i])){
                arrayFraseString[i]=Character.toString(arrayFraseChar[i]);arrayFraseString[i]+=(char)arrayFraseChar[i+1];
                arrayFraseString[i]+=(char)arrayFraseChar[i+2];arrayFraseString[i]+=(char)arrayFraseChar[i+3];

                System.out.println(arrayFraseString[i]);
                arrayFraseInt[contador]=mapita.get(arrayFraseString[i]);
                System.out.println(arrayFraseInt[contador]);
                if(i+3<l){
                    i+=3;
                }



            }else{

                arrayFraseInt[contador] = arrayFraseChar[i];
                System.out.println(arrayFraseInt[contador]);
            }
            contador++;
        }
        System.out.println(Arrays.toString(arrayFraseInt));



        //  Para frase y clave:
        //  Recorrer cada caracter, obtener valor ASCII y después su valor en número binario
        // (terminamos con un arreglo de strings del codigo binario de cada letra)
        for (int i=0; i<clave.length(); i++){
            //FRASE

            //arrayFraseInt[i] = arrayFraseChar[i]; //Obtener valor ASCII

            arrayFraseInt[i]= Integer.parseInt(Integer.toBinaryString(arrayFraseInt[i])); //Volver binario

            arrayFraseString[i]= String.format("%7s", Integer.toString(arrayFraseInt[i])).replace(' ', '0'); //Guardar como array de strings


            //CLAVE
            arrayClaveInt[i] = arrayClaveChar[i]; //Obtener valor ASCII
            arrayClaveInt[i]= Integer.parseInt(Integer.toBinaryString(arrayClaveInt[i])); //Volver binario

            arrayClaveString[i]= String.format("%7s", Integer.toString(arrayClaveInt[i])).replace(' ', '0'); //Guardar como array de strings de longitud 7

        }

        //Revisar variables en binario
        System.out.println(Arrays.toString(arrayFraseString));
        System.out.println(Arrays.toString(arrayClaveString));


        //USAR XOR

        String[] arrayXOR= new String[l];
        int[] arrayXORInt = new int[l];
        String[] arrayXORString = new String[l];


        // ciclo for que recorre cada digito de frase y clave para compararlos
        for (int bloque = 0; bloque<clave.length(); bloque++){
            for (int digito=0; digito<7;digito++){
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

        // convertir el resultado XOR  de valor ASCII  a caracteres
        int posicion= 0;
        for(int n:arrayXORInt){

            if(n<=32){ //check non-printable characters
                arrayXORString[posicion]=nonPrint[n];
            }else{
                arrayXORString[posicion]= String.valueOf((char)n).toLowerCase(); // printable characters a minúscula

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
                    System.out.println("Tu palabra encriptada es:");
                    System.out.println(encriptar(frase,clave));
                    break;
                case 2:
                    System.out.println("Escriba la frase que desea desencriptar: ");
                    scan.nextLine();
                    frase = scan.nextLine();
                    System.out.println("Escriba la clave que desea utilizar (debe ser de la misma longitud que la frase original)");
                    clave = scan.nextLine();
                    System.out.println(desencriptar(frase,clave));
                    break;


                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        }




    }
}