package Application;

import Controlador.ControladorEjemplar;
import Controlador.ControladorLibro;
import Controlador.ControladorPrestamo;
import Controlador.ControladorUsuario;
import Modelo.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorUsuario controladorUsuario=new ControladorUsuario();
        ControladorPrestamo controladorPrestamo=new ControladorPrestamo();
        System.out.print("Introduzca el id del usuario con el que quiere entrar: ");
        int idUsuario=sc.nextInt();
        sc.nextLine();
        if(controladorUsuario.getUsuarioById(idUsuario)!=null){
            opera(idUsuario);
        }else
            System.out.println("El usuario no existe");

    }

    public static void opera(int idUsuario){
        Scanner sc = new Scanner(System.in);
        ControladorUsuario controladorUsuario=new ControladorUsuario();
        ControladorPrestamo controladorPrestamo=new ControladorPrestamo();
        ControladorEjemplar controladorEjemplar=new ControladorEjemplar();
        ControladorLibro controladorLibro=new ControladorLibro();
        String rol=controladorUsuario.getUsuarioById(idUsuario).getTipo();
        int indice=0;
        do {
            System.out.println("¿Que operación quiere realizar?");
            System.out.println("1 - Gestion de libros");
            System.out.println("2 - Gestion de ejemplares");
            System.out.println("3 - Gestion de usuarios");
            System.out.println("4 - Gestion de préstamos");
            System.out.println("5 - Salir");
            indice=sc.nextInt();
            sc.nextLine();
            switch (indice){
                case 1:
                    System.out.print("Introduzca el ISBN del libro: ");
                    String ISBN=sc.nextLine();
                    System.out.print("Introduzca el titulo del libro: ");
                    String titulo=sc.nextLine();
                    System.out.println("Introduzca el autor del libro: ");
                    String autor=sc.nextLine();
                    controladorLibro.addLibro(new Libro(ISBN,titulo,autor));
                    System.out.println("Libro agregado correctamente");
                    break;
                case 2:
                    System.out.println("1 - Registrar ejemplar");
                    System.out.println("2 - Mostrar stock de ejemplares segun ISBN");
                    System.out.print("Introduzca la operación a realizar: ");
                    int indiceEjemplar=sc.nextInt();
                    sc.nextLine();
                    switch (indiceEjemplar){
                        case 1:
                            System.out.print("Introduzca el ISBN del libro(Si el ISBN del libro no existe no se introducirá ningun ejemplar): ");
                            String ISBNEjemplarAdd=sc.nextLine();
                            System.out.print("Introduzca el estado del ejemplar(Disponible/Prestado/Dañado):");
                            String estado=sc.nextLine();
                            if(controladorLibro.getLibroByISBN(ISBNEjemplarAdd)!=null && (estado.equals("Disponible") || estado.equals("Prestado") || estado.equals("Dañado"))) {
                                controladorEjemplar.addEjemplar(new Ejemplar(controladorLibro.getLibroByISBN(ISBNEjemplarAdd),estado));
                            }else
                                System.out.println("No existe el libro con ISBN o el estado del ejemplar no esta introducido correctamente");
                            break;
                        case 2:
                            System.out.print("Introduzca el ISBN del libro a buscar: ");
                            String ISBNEjemplar=sc.nextLine();
                            System.out.println("Su cantidad de ejemplares para el libro con IBSN "+ISBNEjemplar+" es: "+controladorEjemplar.getCountEjemplaresDisponiblesByISBN(ISBNEjemplar));
                            break;
                        default:
                            System.out.println("No ha seleccionado una opcion valida.");
                    }
                    break;
                case 3:
                    System.out.println("1 - Registrar usuario");
                    System.out.println("2 - Registrar penalización");
                    int indiceUsuario=sc.nextInt();
                    sc.nextLine();
                    switch (indiceUsuario){
                        case 1:
                            System.out.print("Introduzca el DNI del usuario: ");
                            String DNI=sc.nextLine();
                            System.out.print("Introduzca el nombre del usuario:");
                            String nombreUsuario=sc.nextLine();
                            System.out.print("Introduzca el email del usuario:");
                            String emailUsuario=sc.nextLine();
                            System.out.print("Introduzca la password del usuario:");
                            String passwordUsuario=sc.nextLine();
                            System.out.println("Introduzca el tipo del usuario(Administrador/Normal): ");
                            String tipoUsuario=sc.nextLine();
                            if(tipoUsuario.equals("Administrador") || tipoUsuario.equals("Normal")){
                                controladorUsuario.addUsuario(new Usuario(DNI, nombreUsuario,emailUsuario,passwordUsuario,tipoUsuario,null));
                            }else
                                System.out.println("El tipo del usuario no es correcto");
                            break;
                        case 2:
                            System.out.print("Introduzca el id del usuario a penalizar: ");
                            int idUser=sc.nextInt();
                            sc.nextLine();
                            System.out.print("Introduzca la fecha penalización para ese usuario(YYYY-MM-DD):");
                            String fecha=sc.nextLine();
                            LocalDate penalizacion=LocalDate.parse(fecha);
                            Usuario user=controladorUsuario.getUsuarioById(idUser);
                            controladorUsuario.updateUsuario(new Usuario(idUser, user.getDni(),user.getNombre(),user.getEmail(),user.getPassword(),user.getTipo(),penalizacion));
                            break;
                        default:
                            System.out.println("No ha seleccionado una opcion valida.");
                    }
                    break;
                case 4:
                    System.out.println("1 - Registrar préstamo");
                    System.out.println("2 - Calcular fecha límite de devolución");
                    System.out.println("3 - Registrar devolución de prestamo");
                    int indicePrestamo=sc.nextInt();
                    sc.nextLine();
                    switch (indicePrestamo){
                        case 1:
                            System.out.print("Introduzca el id del ejemplar: ");
                            int idEjemplar=sc.nextInt();
                            if(controladorEjemplar.getEjemplarById(idEjemplar)!=null)
                                controladorPrestamo.addPrestamo(new Prestamo(controladorUsuario.getUsuarioById(idUsuario),controladorEjemplar.getEjemplarById(idEjemplar),LocalDate.now(),null));
                            else
                                System.out.println("No existe el ejemplar con ese id");
                            break;
                        case 2:
                            System.out.println("Su fecha límite de devolución es: "+LocalDate.now().getDayOfMonth()+15+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear());
                            break;
                        case 3:
                            System.out.println("Introduzca el id del prestamo a actualizar: ");
                            int idPrestamo=sc.nextInt();
                            System.out.print("Introduzca la fecha de devolución: ");
                            String fechaDevolución=sc.nextLine();
                            if(controladorPrestamo.getPrestamoById(idPrestamo)!=null){
                                Prestamo p=controladorPrestamo.getPrestamoById(idPrestamo);
                                controladorPrestamo.updatePrestamo(new Prestamo(p.getId(),p.getUsuario(),p.getEjemplar(),p.getFechaInicio(),LocalDate.parse(fechaDevolución)));
                            }else{
                                System.out.println("No existe el prestamo con ese id");
                            }


                            break;
                        default:
                            System.out.println("No ha seleccionado una opcion valida.");
                    }
                    break;
                case 5:
                    System.out.println("Ha salido de la consola");
                    break;
                default:
                    System.out.println("No ha seleccionado una opción valida");
            }
        }while(indice!=5);
    }

}
