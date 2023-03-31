package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo {

	public ModeloCascada(IFuenteDatos fuenteDatos) {
		if (fuenteDatos == null) {
			throw new NullPointerException("ERROR: La fuente de datos no puede ser nula.");
		}
		clientes = fuenteDatos.crearClientes();
		vehiculos = fuenteDatos.crearVehiculos();
		alquileres = fuenteDatos.crearAlquileres();
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(new Cliente(cliente));
	}

	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo instanceof Turismo) {
			vehiculos.insertar(new Turismo((Turismo) vehiculo));
		}
		if (vehiculo instanceof Furgoneta) {
			vehiculos.insertar(new Furgoneta((Furgoneta) vehiculo));
		}
		if (vehiculo instanceof Autobus) {
			vehiculos.insertar(new Autobus((Autobus) vehiculo));
		}
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		Cliente cliente = clientes.buscar(alquiler.getCliente());
		Vehiculo vehiculo = vehiculos.buscar(alquiler.getVehiculo());

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehiculo del alquiler.");
		}

		alquileres.insertar(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler()));

	}

	public Cliente buscar(Cliente cliente) {
		Cliente clienteBuscar = clientes.buscar(cliente);
		if (clienteBuscar != null) {
			return new Cliente(clienteBuscar.getNombre(), clienteBuscar.getDni(), clienteBuscar.getTelefono());
		} else {
			return null;
		}
	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoBuscar = vehiculos.buscar(vehiculo);
		if (vehiculoBuscar != null) {
			if (vehiculoBuscar instanceof Turismo) {
				Turismo turismo = (Turismo) vehiculoBuscar;
				return new Turismo(turismo.getMarca(), turismo.getModelo(), turismo.getMatricula(), turismo.getCilindrada());
			} else if (vehiculoBuscar instanceof Furgoneta) {
				Furgoneta furgoneta = (Furgoneta) vehiculoBuscar;
				return new Furgoneta(furgoneta.getMarca(), furgoneta.getModelo(), furgoneta.getPma(), furgoneta.getPlazas(), furgoneta.getMatricula());
			} else {
				Autobus autobus = (Autobus) vehiculoBuscar;
				return new Autobus(autobus.getMarca(), autobus.getModelo(), autobus.getMatricula(), autobus.getPlazas());
			}
		} else {
			return null;
		}
	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerDevolver;
		alquilerDevolver = alquileres.buscar(alquiler);
		if (alquilerDevolver != null) {
			return new Alquiler(alquilerDevolver.getCliente(), alquilerDevolver.getVehiculo(), alquilerDevolver.getFechaAlquiler());
		} else {
			return null;
		}
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler alquilerADevolver;
		alquilerADevolver = alquileres.buscar(alquiler);
		if (alquilerADevolver == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		alquilerADevolver.devolver(fechaDevolucion);
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get(cliente)) {
			alquileres.borrar(alquiler);
		}
		clientes.borrar(cliente);
	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get(vehiculo)) {
			alquileres.borrar(alquiler);
		}
		vehiculos.borrar(vehiculo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	public List<Cliente> getClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		for (Cliente cliente : clientes.get()) {
			listaClientes.add(new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono()));
		}
		return listaClientes;
	}

	public List<Vehiculo> getVehiculos() {
		List<Vehiculo> listaVehiculos = new ArrayList<>();
		for (Vehiculo vehiculo : vehiculos.get()) {
			if (vehiculo instanceof Turismo) {
				Turismo turismo = (Turismo) vehiculo;
				listaVehiculos.add(new Turismo(turismo.getMarca(), turismo.getModelo(), turismo.getMatricula(), turismo.getCilindrada()));
			}
			if (vehiculo instanceof Furgoneta) {
				Furgoneta furgoneta = (Furgoneta) vehiculo;
				listaVehiculos.add(new Furgoneta(furgoneta.getMarca(), furgoneta.getModelo(), furgoneta.getPma(), furgoneta.getPlazas(), furgoneta.getMatricula()));
			}
			if (vehiculo instanceof Autobus) {
				Autobus autobus = (Autobus) vehiculo;
				listaVehiculos.add(new Autobus(autobus.getMarca(), autobus.getModelo(), autobus.getMatricula(), autobus.getPlazas()));
			}
		}
		return listaVehiculos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get()) {
			listaAlquileres.add(alquiler);
		}
		return listaAlquileres;
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {
		List<Alquiler> listaAlquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get(cliente)) {
			listaAlquileresCliente.add(alquiler);
		}
		return listaAlquileresCliente;
	}

	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
		List<Alquiler> listaAlquileresVehiculo = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get(vehiculo)) {
			listaAlquileresVehiculo.add(alquiler);
		}
		return listaAlquileresVehiculo;
	}

}
