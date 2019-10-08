package conexionBBDD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import laboral.DatosNoCorrectosException;
import laboral.Empleado;
import laboral.Nomina;

public class fichero {

	public static void main(String[] args) throws IOException, DatosNoCorrectosException {

		//Creamos fichero de empleados
				File empleados = new File("empleados.txt");
				
				FileReader fr = new FileReader (empleados);
				BufferedReader br = new BufferedReader(fr);
				String lectura1 = br.readLine();
				//leemos y guardamos en String
				String[] registro1 = lectura1.split(",");
				
				
				
				Empleado emp1 = new Empleado(registro1[0], registro1[1], registro1[2].charAt(0), Integer.parseInt(registro1[3]), Integer.parseInt(registro1[4]));
				
				
				String lectura2 = br.readLine();
				String[] registro2 = lectura2.split(",");
				
				br.close();
				Empleado emp2 = new Empleado(registro2[0], registro2[1], registro2[2].charAt(0));
				
				System.out.println(escribe(emp1, emp2));
				
				//establecemos categoria 9 para empleado1
				emp1.setCategoria(9);
				registro1[3] = Integer.toString(emp1.getCategoria());
				//incrementamos años trabajados empleado 2
				emp2.incrAnyo();
				registro2[3] = Integer.toString(emp2.anyos);
				
				empleados.delete();
				FileWriter fw = new FileWriter(empleados);
				BufferedWriter bw = new BufferedWriter(fw);
				
				//recorremos y escribimos
				for (int i = 0; i < registro1.length; i++) {
					bw.write(registro1[i] + ",");
				}
				bw.write("\n");
				
				for (int i = 0; i < registro2.length; i++) {
					bw.write(registro2[i] + ",");
				}
				bw.close();
				
				
				 FileOutputStream fichero = new FileOutputStream("datos.dat");
				 DataOutputStream out = new DataOutputStream(fichero);
				
				 fichero.write(registro1[1].getBytes());
				 fichero.write(Nomina.sueldo(emp1));
				 fichero.write(registro2[2].getBytes());
				 fichero.write(Nomina.sueldo(emp2));
				
				
	}
	
	//método para escribir los datos de los empleados
	private static String escribe(Empleado emp1, Empleado emp2) {
		return emp1.toString() + " Gana: " + Nomina.sueldo(emp1) + emp2.toString() + " Gana: " + Nomina.sueldo(emp2);
	}

}
