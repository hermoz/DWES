package conexionBBDD;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import laboral.DatosNoCorrectosException;
import laboral.Empleado;
import laboral.Nomina;

public class conexionBBDD {

	//declaramos primero las variables
	Connection con;
	Statement stmt;
	ResultSet rs;

	public conexionBBDD() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//establecemos la conexion
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "root", "root");
	}

	public void altaEmpleado(Empleado emp) throws SQLException {
		stmt = con.createStatement();
		String query = "Insert into Empleados values ('" + emp.nombre + "','" + emp.dni + "','" + emp.sexo + "'," + emp.getCategoria() + "," + emp.anyos + ")";
		stmt.executeUpdate(query);
		String salario = "insert into Nominas values (" + Nomina.sueldo(emp) + ',' + emp.dni + ")";
		stmt.execute(salario);
		
	}
	
	public void altaEmpleados(File fichero) throws FileNotFoundException, NumberFormatException, DatosNoCorrectosException, SQLException {
		List<Empleado> empleados = new ArrayList<Empleado>();
		Scanner sc = new Scanner(fichero);
		
		String[] registro;
		while(sc.hasNext()) {
			registro = sc.nextLine().split(",");
			if(registro.length > 3) {
				empleados.add(new Empleado(registro[0], registro[1], registro[2].charAt(0), Integer.parseInt(registro[3]), Integer.parseInt(registro[4])));
			}else {
				empleados.add(new Empleado(registro[0], registro[1], registro[2].charAt(0)));
			}
		}
		
		for (Empleado emp : empleados) {
			altaEmpleado(emp);
		}
		
	}

	public List<String> mostrarEmpleados() throws ClassNotFoundException, SQLException {
		stmt = con.createStatement();

		List<String> empleados = new ArrayList<String>();

		rs = stmt.executeQuery("select * from Empleados");

		while (rs.next()) {
			char sexo = rs.getString(3).charAt(0);
			String empleado = rs.getString(1) + "," + rs.getString(2) + "," + sexo + "," + rs.getInt(4) + ","
					+ rs.getInt(5);
			empleados.add(empleado);
		}

		return empleados;

	}

	public int buscarSalario(String dni) throws SQLException {
		int salario = 0;
		String query = "select salario from nominas where dni = ?";
		PreparedStatement ps = this.con.prepareStatement(query);
		ps.setString(1, dni);
		rs = ps.executeQuery();
		while (rs.next()) {
			salario = rs.getInt("salario");
		}

		return salario;
	}
	

	public Empleado buscarEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
		Empleado empleado = null;
		String query = "select * from Empleados where dni like ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, dni);
		rs = ps.executeQuery();

		
		while(rs.next()) {
			empleado = new Empleado(rs.getString("nombre"), 
					rs.getString("dni"), 
					rs.getString("sexo").charAt(0),
					rs.getInt("categoria"),
					rs.getInt("anyos"));
		}
		
		return empleado;
	}
	
	public void editarEmpleado(Empleado emp) throws SQLException {
		String query = "update empleados set nombre = ?, sexo = ?, categoria = ?, anyos = ? where dni = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, emp.nombre);
		ps.setString(2, Character.toString(emp.sexo));
		ps.setInt(3, emp.getCategoria());
		ps.setInt(4, emp.anyos);
		ps.setString(5, emp.dni);

		ps.executeUpdate();
		calcularSueldo(emp);
		
	}

	
	public void calcularSueldo(Empleado emp) throws SQLException {
		
		String query = "update nominas set salario = "+Nomina.sueldo(emp)+" where dni = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, emp.dni);
		ps.executeUpdate();
	}
	
	
}
