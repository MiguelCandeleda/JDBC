package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Practica2 {

	public Practica2() {
		// TODO Auto-generated method stub

		// SEGUNDO TALLER

		System.out.println("---------------------------------------------------");
		System.out.println("Inicio práctica 2");
		System.out.println("---------------------------------------------------");

		Connection conn2 = null;

		try {
			conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

			System.out.println("Conexión creada");
		} catch (SQLException e) {
			System.err.println("Error al crear la conexión de la base de datos");
			e.printStackTrace();

		}
		if (conn2 != null) {
			PreparedStatement stmt2 = null;

			// Creamos variables que vamos a pedir por consola
			Integer id_entrada;
			String first_name;
			String last_name;
			String address;
			String city;
			String telephone;

			Scanner leer = new Scanner(System.in);

			System.out.print("Introduzca el Id: ");
			id_entrada = leer.nextInt();
			System.out.print("Introduzca el nombre: ");
			first_name = leer.next();
			System.out.print("Introduzca el apellido: ");
			last_name = leer.next();
			System.out.print("Introduzca la dirección: ");
			address = leer.next();
			System.out.print("Introduzca la ciudad: ");
			city = leer.next();
			System.out.print("Introduzca el telefóno: ");
			telephone = leer.next();

			String sql = "INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES(?,?,?,?,?)";

			try {
				stmt2 = conn2.prepareStatement(sql);
				stmt2.setString(1, first_name);
				stmt2.setString(2, last_name);
				stmt2.setString(3, address);
				stmt2.setString(4, city);
				stmt2.setString(5, telephone);
			} catch (SQLException e) {
				System.err.println("Error al crear el statement para la consulta");
				e.printStackTrace();
			} finally {
				try {
					if (stmt2 != null)
						stmt2.close();
				} catch (SQLException e) {
					System.err.println("Error al cerrar el statement");
					e.printStackTrace();
				} finally {
					try {
						if (conn2 != null)
							conn2.close();
					} catch (SQLException e) {
						System.err.println("Error al cerrar la conexión de la base de datos");
					}
				}
			}

		}

	}

}
