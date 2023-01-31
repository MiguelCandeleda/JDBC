package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;

public class RetoJDBC {

	private Connection con;
	private PreparedStatement ps2;
	private static PreparedStatement ps;
	//Generamos las 2 consultas SQL para insertar al propietario y a su mascota
	private static String sql = "INSERT INTO OWNER (id, first_name, last_name, address, city, telephone) VALUES (?,?,?,?,?,?)";
	private static String sql_pets = "INSERT INTO PET (id, name, birthdate, id_owner) VALUES (?,?,?,?)";

	public RetoJDBC() {
		// Reto final JDBC
		System.out.println("---------------------------------------------------");
		System.out.println("Reto final");
		System.out.println("---------------------------------------------------");

		// Instaciamos al objeto propietario
		Owner propietario = new Owner();

		// Rellenamos todos sus valores
		propietario.setId(2);
		propietario.setFirstName("Miguel");
		propietario.setLastName("Acosta");
		propietario.setCity("Salamanca");
		propietario.setTelephone("556633434");

		// Realizamos una conexión a BD para que lo inserte

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");
			ps = con.prepareStatement(sql);
			System.out.println("ESTABLECEMOS LA CONEXION CON LA BD");
		} catch (SQLException e) {
			System.err.println("Se ha producido un error al estableces la conexi�n");
			e.printStackTrace();
		}
		
		try {
			System.out.println("Preparamos la consulta a lanzar parametrizada");
			
			ps.setInt(1, propietario.getId());
			ps.setString(2, propietario.getFirstName());
			ps.setString(3, propietario.getLastName());
			ps.setString(4, propietario.getAddress());
			ps.setString(5, propietario.getCity());
			ps.setString(6, propietario.getTelephone());
			
			boolean result = ps.execute();
			
			if (result) {
				System.out.println("Valores insertados correctamente");
				
				//Lo siguiente es insertarle una mascota
				Pet p = new Pet();
				p.setBirthdate(new Date(1999, 3, 20));
				p.setId(15);
				p.setName("Mordisquitos");
				//Insertamos el id del propietario de la mascota
				p.setId_owner(propietario.getId());
				
				//Realizamos la inserción de los datos de la mascota en la BD
				

				System.out.println("Preparamos la consulta a lanzar parametrizada sobre mascotas");
				ps2 = con.prepareStatement(sql_pets);
				ps2.setInt(1, p.getId());
				ps2.setString(2, p.getName());
				ps2.setDate(3, p.getBirthDate());
				ps2.setInt(4, p.getId_owner());

				boolean result2 = ps2.execute();
				
				if (result2) {
					System.out.println("Datos introducidos correctamente");
				}else {
					System.out.println("Error en la introducción de datos");
				}
				
				
			}else {
				System.out.println("No se han podido instertar los datos");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				ps2.close();
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
