package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Practica4 {

	public Practica4() {
		// TODO Auto-generated method stub

		// Cuarto taller

		System.out.println("---------------------------------------------------");
		System.out.println("Inicio práctica 4");
		System.out.println("---------------------------------------------------");

		Connection conn4 = null;

		try {
			conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

			System.out.println("Conexión creada");
		} catch (SQLException e) {
			System.err.println("Error al crear la conexión de la base de datos");
			e.printStackTrace();

		}
		if (conn4 != null) {
			PreparedStatement stmt4 = null;

			// Creamos variables que vamos a pedir por consola
			String nombre4;
			String apellidos4;

			Scanner leer4 = new Scanner(System.in);

			System.out.print("Introduzca el nombre");
			nombre4 = leer4.next();
			System.out.print("Introduzca el apellido");
			apellidos4 = leer4.next();

			String sql4 = "SELECT * from owners WHERE first_name = ? AND last_name = ?";

			try {
				stmt4 = conn4.prepareStatement(sql4);
				stmt4.setString(1, nombre4);
				stmt4.setString(2, apellidos4);

			} catch (SQLException e) {
				System.err.println("Error al crear el statement para el ejercicio 4");
				e.printStackTrace();
			} finally {
				try {
					if (stmt4 != null)
						stmt4.close();
				} catch (SQLException e) {
					System.err.println("Error al cerrar el statement");
					e.printStackTrace();
				} finally {
					try {
						if (conn4 != null)
							conn4.close();
					} catch (SQLException e) {
						System.err.println("Error al cerrar la conexión de la base de datos");
					}
				}
			}

		}

	}

}
