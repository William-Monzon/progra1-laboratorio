package introduccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
    static List<String> estudiantes = new ArrayList<>();
    static List<Double> calificaciones = new ArrayList<>();
    
    public static void main(String[] args) {
    	int opcion;
    	System.out.println("Bienvenido al sistema de gestión de estudiantes.");
    	do {
    		System.out.println("\n1. Agregar estudiante");
            System.out.println("2. Mostrar lista de estudiantes");
            System.out.println("3. Calcular promedio de calificaciones");
            System.out.println("4. Mostrar estudiante con la calificación más alta");
            System.out.println("5. Salir");
            opcion = validacionMenu(0);
	        switch(opcion) {
		        case 1:
		        	agregarEstudiante();
		        	pausar();
		        break;
		        case 2:
		        	listaEstudiantes();
		        	pausar();
		        break;
		        case 3: 
		        	promedioCalificaciones();
		        	pausar();
		        break;
		        case 4: 
		        	calificacionAlta();
		        	pausar();
		        break;
		        case 5: 
		        	System.out.println("Saliendo del sistema...");
		        break;
		        default: System.out.println("Opción no válida. Intente de nuevo.");
	        }
    	}while(opcion!=5);
        sc.close();
    }
    public static void agregarEstudiante() {
    	String nombre =validacionNombre(null);
    	double calificacion = validacionCalificacion(0);

    	estudiantes.add(nombre);
    	calificaciones.add(calificacion);

    	System.out.println("Estudiante agregado correctamente.");
    }
    public static void listaEstudiantes() {
    	  if (estudiantes.isEmpty()) {
              System.out.println("No hay estudiantes registrados.");
          } else {
              System.out.println("\nLista de estudiantes:");
              for (int i = 0; i < estudiantes.size(); i++) {
                  System.out.println(estudiantes.get(i) +" - Calificación: " + calificaciones.get(i));
              }
          }
    }
    public static void promedioCalificaciones() {
    	 if (calificaciones.isEmpty()) {
             System.out.println("No hay calificaciones registradas.");
         } else {
             double suma = 0;
             for (double calificacion : calificaciones) {
                 suma += calificacion;
             }
             double promedio = suma / calificaciones.size();
             System.out.println("El promedio de calificaciones es: " + promedio);
         }
    }
    public static void calificacionAlta() {
    	 if (calificaciones.isEmpty()) {
             System.out.println("No hay calificaciones registradas.");
         } else {

             double maxCalificacion = calificaciones.get(0);
             String estudianteMax = estudiantes.get(0);

             for (int i = 1; i < calificaciones.size(); i++) {
                 if (calificaciones.get(i) > maxCalificacion) {
                     maxCalificacion = calificaciones.get(i);
                     estudianteMax = estudiantes.get(i);
                 }
             }
             System.out.println("El estudiante con la calificación más alta es: " + estudianteMax + " con " + maxCalificacion);
         }
    }
    public static void pausar() {
    	System.out.println("\nPresione Enter para continuar...");
    	sc.nextLine();
    }
    public static int validacionMenu(int opcion){ 
		boolean validar = false; 
		do {
			try {
				System.out.print("Seleccione una opción: ");
				opcion = Integer.parseInt(sc.nextLine());
		        if (opcion <0) {
		        	throw new IllegalArgumentException(); 
		        }
		        validar = true;
			} catch (NumberFormatException e) {
		        System.out.println("Error: Usted no ingreso un número.");
		        pausar();
			} catch (IllegalArgumentException e) {
		        System.out.println("Error: Usted ingreso un número negativo.");
		        pausar(); 
			}	
		} while(!validar);
		return opcion;
	}
    public static double validacionCalificacion(double nota){ 
		boolean validar = false; 
		do {
			try {
				System.out.print("Ingrese la calificación del estudiante: ");
				nota = Double.parseDouble(sc.nextLine()); 
				if (nota <0 || nota>100) {
		        	throw new IllegalArgumentException(); 
				} validar = true;
			} catch (NumberFormatException e) {
		        System.out.println("Error: Usted no ingreso un número.");
		        pausar();
			} catch (IllegalArgumentException e) {
		        System.out.println("Error: Usted ingreso un número inválido.");
		        pausar();
			}		
		} while(!validar);
		return nota;
	}
    public static String validacionNombre(String nombre){ 
		boolean validar = false; 
		do {
			try {
				System.out.print("Ingrese el nombre del estudiante: ");
				nombre = sc.nextLine(); 
				if (nombre.isEmpty()) {
					throw new IllegalArgumentException(); 
				} validar = true;
			} 
			catch (IllegalArgumentException e) {
		        System.out.println("Error: Usted dejo el campo vacío.");
		        pausar();
			}		
		} while(!validar);
		return nombre;
	}
}
