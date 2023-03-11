package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private final static String PATRON_FECHA = "^([0-2][0-9]|3[0-1])(\\/)(0[1-9]|1[0-2])\\2(\\d{4})$";
	private final static String PATRON_MES = "^[1-12]$";
	private final static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		String subrayado = "";
		for (int i = 0; i < mensaje.length(); i++) {
			subrayado = subrayado + "-";
		}

		System.out.println(mensaje);
		System.out.println(subrayado);
	}

	public static void mostrarMenuAcciones() {
		Accion[] accion = Accion.values();
		System.out.println("-------------------------------------------");
		System.out.println("ALQUILER DE TURISMO, FURGONETAS Y AUTOBUSES");
		System.out.println("-------------------------------------------");
		System.out.println("ACCIONES DISPONIBLES");
		System.out.println("--------------------");

		for (int i = 0; i < accion.length; i++) {
			System.out.println(i + ".- " + accion[i].toString());
		}
	}

	private static String leerCadena(String mensaje) {
		System.out.println(mensaje);
		return Entrada.cadena();

	}

	private static Integer leerEntero(String mensaje) {
		System.out.println(mensaje);
		return Entrada.entero();
	}

	public static LocalDate leerMes() {

		String mes = "";
		do {
			leerCadena("Introduzca mes:");
		} while (!mes.matches(PATRON_MES));
		int mesNumerico = Integer.parseInt(mes);
		return LocalDate.of(2023, mesNumerico, 1);
	}

	private static LocalDate leerFecha(String mensaje) {
		String fecha;
		LocalDate fechaDevolver = null;

		System.out.println(mensaje);
		fecha = Entrada.cadena();
	
		while (!fecha.matches(PATRON_FECHA)) {
			System.out.println("Fecha incorrecta, vuelva a introducirla: ");
			fecha = Entrada.cadena();
		}
		try {
			fechaDevolver = LocalDate.parse(fecha, FORMATO_FECHA);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: EL formato de la fecha es inválido.");
		}
		return fechaDevolver;
	}

	public static Accion elegirAccion() {
		boolean comprobador = false;
		Accion accion = null;
		int ordinal = leerEntero("\nIntroduzca un accion: ");
		while (comprobador == false) {
			try {
				accion = Accion.get(ordinal);
				comprobador = true;
			} catch (Exception e) {
				System.out.println("Vuelva a introducir una accion correcta: ");
				ordinal = Entrada.entero();
			}
		}
		return accion;
	}

	public static Cliente leerCliente() {
		String nombre = leerCadena("Introduzca nombre de cliente: ");
		String dni = leerCadena("Introduzca dni del cliente: ");
		String telefono = leerCadena("Introduzca telefono de cliente: ");
		return new Cliente(nombre, dni, telefono);
	}

	public static Cliente leerClienteDni() {
		String dni = leerCadena("Introduzca dni del cliente: ");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		return leerCadena("Introduzca nombre: ");
	}

	public static String leerTelefono() {
		return leerCadena("Introduzca telefono: ");
	}

	public static Vehiculo leerVehiculo() {

		int cilindrada, plazas, pma;
		String marca, modelo, matricula;

		mostrarMenuTipoVehiculo();
		Vehiculo vehiculo = leerVehiculo(elegirTipoVehiculo());
		Vehiculo vehiculoInsertar = null;

		if (TipoVehiculo.get(vehiculo).equals(TipoVehiculo.TURISMO)) {
			marca = leerCadena("\nIntroduzca marca de turismo: ");
			modelo = leerCadena("Introduzca modelo de turismo: ");
			matricula = leerCadena("Introduzca matricula de turismo: ");
			cilindrada = leerEntero("Introduzca cilindrada de turismo: ");

			vehiculoInsertar = new Turismo(marca, modelo, matricula, cilindrada);
		}
		if (TipoVehiculo.get(vehiculo).equals(TipoVehiculo.FURGONETA)) {
			marca = leerCadena("\nIntroduzca marca de la furgoneta: ");
			modelo = leerCadena("Introduzca modelo de la furgoneta: ");
			matricula = leerCadena("Introduzca matricula de la furgoneta: ");
			pma = leerEntero("Introduzca masa máxima autorizada de la furgoneta: ");
			plazas = leerEntero("Introduzca plazass de la furgoneta: ");

			vehiculoInsertar = new Furgoneta(marca, modelo, pma, plazas, matricula);
		}
		if (TipoVehiculo.get(vehiculo).equals(TipoVehiculo.AUTOBUS)) {
			marca = leerCadena("\nIntroduzca marca del autobus: ");
			modelo = leerCadena("Introduzca modelo del autobus: ");
			matricula = leerCadena("Introduzca matricula del autobus: ");
			plazas = leerEntero("Introduzca plazass del autobus: ");

			vehiculoInsertar = new Autobus(marca, modelo, matricula, plazas);
		}
		return vehiculoInsertar;
	}

	private static void mostrarMenuTipoVehiculo() {
		System.out.println("---------------------");
		System.out.println("ELEGIR TIPO VEHICULO ");
		System.out.println("---------------------");
		System.out.println("0. Turimos.");
		System.out.println("1. Autobus.");
		System.out.println("2. Furgoneta.\n");
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		boolean comprobador = false;
		TipoVehiculo tipoVehiculo = null;
		int ordinal = leerEntero("Elija un tipo de vehiculo: ");
		while (comprobador == false) {
			try {
				tipoVehiculo = TipoVehiculo.get(ordinal);
				comprobador = true;
			} catch (Exception e) {
				System.out.println("Vuelva a elejir un vehiculo correcto: ");
				ordinal = Entrada.entero();
			}
		}
		return tipoVehiculo;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculo = null;
		switch (tipoVehiculo) {
		case TURISMO:
			vehiculo = new Turismo("Ford", "Mondeo", "1111JJJ", 11);
			break;
		case AUTOBUS:
			vehiculo = new Autobus("Ford", "Mondeo", "1111JJJ", 30);
			break;
		case FURGONETA:
			vehiculo = new Furgoneta("Ford", "Mondeo", 7, 100, "1111JJJ");
			break;
		}
		return vehiculo;

	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = leerCadena("Introduzca matricula de vehiculo: ");
		return Vehiculo.getVehiculoConMatricula(matricula);
	}

	public static Alquiler leerAlquiler() {
		Vehiculo vehiculo = leerVehiculoMatricula();
		Cliente cliente = leerClienteDni();
		LocalDate fechaAlquiler = leerFecha("Introduzca fecha de alquiler: ");

		return new Alquiler(cliente, vehiculo, fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() {
		LocalDate fechaDevolucion = leerFecha("Introduzca fecha de devolución: ");
		return fechaDevolucion;
	}

}
