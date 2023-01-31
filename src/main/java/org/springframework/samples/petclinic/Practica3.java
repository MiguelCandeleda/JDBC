package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Practica3 {

	public Practica3() {
		// TODO Auto-generated method stub

		// TERCER TALLER Pedimos ciudad por consola y actualizamos

		System.out.println("---------------------------------------------------");
		System.out.println("Inicio práctica 3");
		System.out.println("---------------------------------------------------");

		Connection conn3 = null;

		try {
			conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

			System.out.println("Conexión creada");
		} catch (SQLException e) {
			System.err.println("Error al crear la conexión de la base de datos");
			e.printStackTrace();

		}
		if (conn3 != null) {
			Statement stmt3 = null;

			try {
				stmt3 = conn3.createStatement();
			} catch (SQLException e) {
				System.err.println("Error al crear el statement para la consulta");
				e.printStackTrace();
			}

			// Creamos variables que vamos a pedir por consola
			String nombre;

			Scanner leer = new Scanner(System.in);
			;
			System.out.print("Introduzca el nombre");
			nombre = leer.next();

			String sql = "UPDATE owners SET city='Salamanca' WHERE first_name=" + nombre;

			try {

				if (stmt3 != null) {
					ResultSet result = stmt3.executeQuery(sql);
					System.out.print("Ciudad modificada correctamente...");
				}
			} catch (SQLException e) {
				System.err.println("Error al modificar los datos");
				e.printStackTrace();
			} finally {
				try {
					if (stmt3 != null)
						stmt3.close();
				} catch (SQLException e) {
					System.err.println("Error al cerrar el statement");
					e.printStackTrace();
				} finally {
					try {
						if (conn3 != null)
							conn3.close();
					} catch (SQLException e) {
						System.err.println("Error al cerrar la conexión de la base de datos");
					}

				}

			}

		}

	}
}
