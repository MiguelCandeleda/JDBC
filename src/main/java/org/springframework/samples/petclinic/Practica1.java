package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Practica1 {

	public Practica1() {

		// TODO Auto-generated method stub

		// Práctica 1
		
		System.out.println("---------------------------------------------------");
		System.out.println("Inicio práctica 1");
		System.out.println("---------------------------------------------------");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

			System.out.println("Conexión creada");
		} catch (SQLException e) {
			System.err.println("Error al crear la conexión de la base de datos");
			e.printStackTrace();

		}

		ResultSet result = null;
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				System.err.println("Error al crear el statement para la consulta");
				e.printStackTrace();
			}
			if (conn != null) {
				String sql = "SELECT * FROM owners";
				try {

					if (stmt != null) {
						result = stmt.executeQuery(sql);
					}
				} catch (SQLException e) {
					System.err.println("Error al crear la consulta");
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					if (result != null) {
						while (result.next()) {
							Integer id = result.getInt("id");
							String first_name = result.getString("first_name");
							String last_name = result.getString("last_name");
							String address = result.getString("address");
							String city = result.getString("city");
							String telephone = result.getString("telephone");

							System.out.println(
									"Nombre:" + first_name + "\n " + "Apellido:" + last_name + "\n " + "Dirección:"
											+ address + "\n " + "Ciudad:" + city + "\n " + "Teléfono:" + telephone);

						}
					}
				} catch (SQLException e) {
					System.err.println("Error al obtener los datos de la consulta");
					e.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException e) {
						System.err.println("Error al cerrar el statement");
						e.printStackTrace();
					} finally {
						try {
							if (conn != null)
								conn.close();
						} catch (SQLException e) {
							System.err.println("Error al cerrar la conexión de la base de datos");
						}
					}
				}
			}
		}

	}

}
