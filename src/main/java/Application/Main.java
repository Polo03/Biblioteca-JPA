package Application;

import Modelo.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        DAOGenerico<Usuario> daoUsuario=new DAOGenerico<>(Usuario.class);
        System.out.print("Introduzca el id del usuario con el que quiere entrar: ");
        int idUsuario=sc.nextInt();
        if(daoUsuario.getById(idUsuario)!=null){
            String rol=daoUsuario.getById(idUsuario).getTipo();
            switch (rol){
                case "admin":
                    System.out.println("Su rol es administrador");
                    operaAdmin();
                    break;
                case "normal":
                    System.out.println("Su rol es normal");
                    System.out.println(daoUsuario.getAll());
                    break;
                default:
                    System.out.println("No ha seleccionado una opción valida.");
            }
        }else
            System.out.println("El usuario no existe");*/
        DAOGenerico<Usuario> daoUsuario=new DAOGenerico<>(Usuario.class);
        Usuario usuario = daoUsuario.crearObjeto("47318093D", "Adrián Polo", "adrianpoloper@gmail.com", "1234Abcd", "administrador",null);
        daoUsuario.add(usuario);



    }

    public static void operaAdmin(){
        Scanner sc = new Scanner(System.in);

    }

}
