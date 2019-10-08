package laboral;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import conexionBBDD.conexionBBDD;


public class CalculadoraNominas {

	public static void main(String[] args) throws DatosNoCorrectosException, IOException, ClassNotFoundException, SQLException {
					
		conexionBBDD bbdd = new conexionBBDD();
		boolean salir = false;		
		
		Scanner s1= new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
				
		while (!salir) {
			
			System.out.println("1_Mostrar informacion empleados \n"
					+ "2_Mostrar salario empleado especifico\n"
					+ "3_Modificar datos empleado\n"
					+ "4_Recalcular y actualizar sueldo de empleado \n"
					+ "5_Recalcular y actualizar sueldo de todos los empleados \n"
					+ "Salir");
			
			String menuOpciones= s2.nextLine();
		
			switch (menuOpciones) {
				case "1": 
					
					for (String empleado : bbdd.mostrarEmpleados()) {
						System.out.println(empleado);
					}
					
					break;
					
				case "2":					
					System.out.println("Introduzca DNI: ");
					String dni = s2.nextLine();
					System.out.println(bbdd.buscarSalario(dni));
	
					break;
					
				case "3":
					System.out.println("Introduzca DNI para modificar empleado: ");
					String dniEditar = s2.nextLine();
					String nombreNuevo;
					String sexoNuevo;
					int categoriaNueva;
					int anyosNuevos;
					
					
						System.out.println("Elija campo a editar \n"
								+ "1_ Nombre \n"
								+ "2_ Sexo \n"
								+ "3_ Categoria \n"
								+ "4_ Anyos \n"
								+ "5_ Todos los campos \n"
								+ "Introduzca cualquier otra tecla para salir del menu");
						
						
							Empleado empleadoParaEditar = bbdd.buscarEmpleado(dniEditar);
							String opcionSubmenu = s1.nextLine();
							
						switch (opcionSubmenu) {
						case "1":
							System.out.println("Actualizar nombre");
							nombreNuevo = s2.nextLine();
							bbdd.editarEmpleado(new Empleado(nombreNuevo, dniEditar, empleadoParaEditar.sexo, empleadoParaEditar.getCategoria(), empleadoParaEditar.anyos));
							
							break;
						case "2":
							System.out.println("Actualizar sexo");
							sexoNuevo = s2.nextLine();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, sexoNuevo.charAt(0), empleadoParaEditar.getCategoria(), empleadoParaEditar.anyos));
						
							break;
							
						case "3":
							System.out.println("Actualizar categoria");
							categoriaNueva = s2.nextInt();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, empleadoParaEditar.sexo, categoriaNueva, empleadoParaEditar.anyos));

							break;
						case "4":
							System.out.println("Actualizar anyos");
							anyosNuevos = s1.nextInt();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, empleadoParaEditar.sexo, empleadoParaEditar.getCategoria(), anyosNuevos));

							break;
							
						case "5":
							System.out.println("Nuevo nombre");
							nombreNuevo = s2.nextLine();
							
							System.out.println("Nuevo sexo");
							sexoNuevo = s2.nextLine();
							
							System.out.println("Nueva categoria");
							categoriaNueva = s1.nextInt();
							
							System.out.println("Nuevos anyos");
							anyosNuevos = s1.nextInt();
							
							bbdd.editarEmpleado(new Empleado(nombreNuevo, dniEditar, sexoNuevo.charAt(0), categoriaNueva, anyosNuevos));

							break;
						default:
							System.out.println("");
							break;
						}
						
					
					break;
					
				case "4":
					System.out.println("Introduzca DNI para recalcular el sueldo");
					dniEditar = s2.nextLine();
					bbdd.calcularSueldo(bbdd.buscarEmpleado(dniEditar));
					
					
					break;
					
				case "5":
					//recorremos la base de datos con un for:each
					for(String registro : bbdd.mostrarEmpleados()) {
						dni = registro.split(",")[1];
						bbdd.calcularSueldo(bbdd.buscarEmpleado(dni));
					}
					
					break;
					
					
				default:
					salir=true;
					break;
			}
		
	
		};
			

	}
}
		
	

	
	


