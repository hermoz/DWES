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
			
			System.out.println("1. Ver todos los empleados \n"
					+ "2. Ver salario de un empleado especifico \n"
					+ "3. Submenu de edicion \n"
					+ "4. Recalcular y actualizar sueldo de un empleado \n"
					+ "5. Recalcular y actualizar sueldos de todos los empleados \n"
					+ "6. Realizar copia de seguridad de la BBDD en fichero \n"
					+ "Pulsa cualquier otra tecla para salir");
			String opcionMenu = s2.nextLine();
			
		
			switch (opcionMenu) {
				case "1": 
					
					for (String empleado : bbdd.mostrarEmpleados()) {
						System.out.println(empleado);
					}
					
					break;
				case "2":
					
					System.out.println("Introduce el dni: ");
					String dni = s2.nextLine();
					System.out.println(bbdd.buscarSalario(dni));
					
					
					break;
				case "3":
					System.out.println("Introduce DNI para editar el empleado");
					String dniEditar = s2.nextLine();
					String nombreNuevo;
					String sexoNuevo;
					int categoriaNueva;
					int anyosNuevos;
					
					
						System.out.println("Elige un campo para editar \n"
								+ "1. Nombre \n"
								+ "2. Sexo \n"
								+ "3. Categoria \n"
								+ "4. Anyos \n"
								+ "5. Todos los campos \n"
								+ "Introduce cualquier otra tecla para salir.");
						
						
							Empleado empleadoParaEditar = bbdd.buscarEmpleado(dniEditar);
							String opcionSubmenu = s1.nextLine();
							
						switch (opcionSubmenu) {
						case "1":
							System.out.println("Elija el nuevo nombre");
							nombreNuevo = s2.nextLine();
							bbdd.editarEmpleado(new Empleado(nombreNuevo, dniEditar, empleadoParaEditar.sexo, empleadoParaEditar.getCategoria(), empleadoParaEditar.anyos));
							
							break;
						case "2":
							System.out.println("Elija el nuevo sexo");
							sexoNuevo = s2.nextLine();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, sexoNuevo.charAt(0), empleadoParaEditar.getCategoria(), empleadoParaEditar.anyos));

							
							
							break;
						case "3":
							System.out.println("Elija la nueva categoria");
							categoriaNueva = s2.nextInt();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, empleadoParaEditar.sexo, categoriaNueva, empleadoParaEditar.anyos));

							break;
						case "4":
							System.out.println("Elija los nuevos anyos");
							anyosNuevos = s1.nextInt();
							bbdd.editarEmpleado(new Empleado(empleadoParaEditar.nombre, dniEditar, empleadoParaEditar.sexo, empleadoParaEditar.getCategoria(), anyosNuevos));

							break;
						case "5":
							System.out.println("Elija el nuevo nombre");
							nombreNuevo = s2.nextLine();
							
							System.out.println("Elija el nuevo sexo");
							sexoNuevo = s2.nextLine();
							
							System.out.println("Elija la nueva categoria");
							categoriaNueva = s1.nextInt();
							
							System.out.println("Elija los nuevos anyos");
							anyosNuevos = s1.nextInt();
							
							bbdd.editarEmpleado(new Empleado(nombreNuevo, dniEditar, sexoNuevo.charAt(0), categoriaNueva, anyosNuevos));

							break;
						default:
							System.out.println("");
							break;
						}
						
					
					break;
					
				case "4":
					System.out.println("Introduce el dni para recalcular el sueldo");
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
		
	

	
	


