package Application;

import Modelo.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DAOGenerico<Usuario> dao=new DAOGenerico<>(Usuario.class);
        System.out.print("¿Que rol tiene usted?(admin/normal)");
        String rol=sc.nextLine();
        switch (rol){
            case "admin":
                operaAdmin();
                break;
            case "normal":


                break;
            default:
                System.out.println("No ha seleccionado una opción valida.");
        }

    }

    public static void operaAdmin(){
        Scanner sc = new Scanner(System.in);

    }

}
