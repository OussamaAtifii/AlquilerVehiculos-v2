package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Accion;
import org.iesalandalus.programacion.alquilervehiculos.vista.Consola;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

    public VistaTexto() {

    }

    public void comenzar() {
        Accion accion;

        do {
            Consola.mostrarMenuAcciones();
            accion = Consola.elegirAccion();
            ejecutar(accion);
        } while (accion != Accion.SALIR);
    }

    public void terminar() {
        System.out.println("Vista finalizada");
    }

    protected void ejecutar(Accion accion) {
        switch (accion) {
            case SALIR:
                terminar();
                break;
            case INSERTAR_CLIENTE:
                insertarCliente();
                break;
            case INSERTAR_VEHICULO:
                insertarVehiculo();
                break;
            case INSERTAR_ALQUILER:
                insertarAlquiler();
                break;
            case BUSCAR_CLIENTE:
                buscarCliente();
                break;
            case BUSCAR_VEHICULO:
                buscarVehiculo();
                break;
            case BUSCAR_ALQUILER:
                buscarAlquiler();
                break;
            case MODIFICAR_CLIENTE:
                modificarCliente();
                break;
            case DEVOLVER_ALQUILER:
                devolverAlquiler();
                break;
            case BORRAR_CLIENTE:
                borrarCliente();
                break;
            case BORRAR_VEHICULO:
                borrarVehiculo();
                break;
            case BORRAR_ALQUILER:
                borrarAlquiler();
                break;
            case LISTAR_CLIENTES:
                listarClientes();
                break;
            case LISTAR_VEHICULO:
                listarVehiculos();
                break;
            case LISTAR_ALQUILERES:
                listarAlquileres();
                break;
            case LISTAR_ALQUILERES_CLIENTE:
                listarAlquileresCliente();
                break;
            case LISTAR_ALQUILERES_VEHICULO:
                listarAlquileresVehiculo();
                break;
        }
    }

    protected void insertarCliente() {
        Consola.mostrarCabecera("Insertar cliente");
        try {
            controlador.insertar(Consola.leerCliente());
            System.out.println("------------------------------------");
            System.out.println("✅ - Cliente insertado correctamente");
            System.out.println("------------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar vehiculo");
        try {
            controlador.insertar(Consola.leerVehiculo());
            System.out.println("-------------------------------------");
            System.out.println("✅ - Vehiculo insertado correctamente");
            System.out.println("-------------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void insertarAlquiler() {
        Consola.mostrarCabecera("Insertar alquiler");
        try {
            controlador.insertar(Consola.leerAlquiler());
            System.out.println("-------------------------------------");
            System.out.println("✅ - Alquiler insertado correctamente");
            System.out.println("-------------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            
            if (cliente != null) {
                System.out.println(cliente + "\n");
            } else {
                System.out.println("--------------------------");
                System.out.println("❌ - Cliente no encontrado");
                System.out.println("--------------------------\n");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    protected void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehiculo");
        try {
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());

            if (vehiculo != null) {
                System.out.println(vehiculo + "\n");
            } else {
                System.out.println("---------------------------");
                System.out.println("❌ - Vehiculo no encontrado");
                System.out.println("---------------------------\n");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void buscarAlquiler() {
        Consola.mostrarCabecera("Buscar alquiler");
        try {
            Alquiler alquiler = controlador.buscar(Consola.leerAlquiler());

            if (alquiler != null) {
                System.out.println(alquiler + "\n");
            } else {
                System.out.println("---------------------------");
                System.out.println("❌ - Alquiler no encontrado");
                System.out.println("---------------------------\n");    
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void modificarCliente() {
        Consola.mostrarCabecera("Modificar cliente");
        try {
            controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
            System.out.println("-------------------------------------");
            System.out.println("✅ - Cliente modificado correctamente");
            System.out.println("-------------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void devolverAlquiler() {
        Consola.mostrarCabecera("Devolver alquiler");
        try {
            controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
            System.out.println("------------------------------------");
            System.out.println("✅ - Alquiler devuelto correctamente");
            System.out.println("------------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        try {
            controlador.borrar(Consola.leerClienteDni());
            System.out.println("----------------------------------");
            System.out.println("✅ - Cliente borrado correctamente");
            System.out.println("----------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehiculo");
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
            System.out.println("-----------------------------------");
            System.out.println("✅ - Vehiculo borrado correctamente");
            System.out.println("-----------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarAlquiler() {
        Consola.mostrarCabecera("Borrar alquiler");
        try {
            controlador.borrar(Consola.leerAlquiler());
            System.out.println("-----------------------------------");
            System.out.println("✅ - Alquiler borrado correctamente");
            System.out.println("-----------------------------------\n");
        } catch (OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> clientes = null;

        try {
            clientes = controlador.getClientes();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));

        if (clientes.size() == 0){
            System.out.println("--------------------------------");
            System.out.println("❌ - No hay clientes que mostrar");
            System.out.println("--------------------------------\n");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
            System.out.println("\n");
        }
    }

    protected void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehiculos");
        List<Vehiculo> vehiculos = null;

        try {
            vehiculos = controlador.getVehiculos();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        vehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula));

        if (vehiculos.size() == 0) {
            System.out.println("---------------------------------");
            System.out.println("❌ - No hay vehiculos que mostrar");
            System.out.println("---------------------------------\n");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
            System.out.println("\n");
        }
    }

    protected void listarAlquileres() {
        Consola.mostrarCabecera("Listar Alquileres");
        List<Alquiler> alquileres = null;

        try {
            alquileres = controlador.getAlquileres();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
        alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparadorCliente));

        if (alquileres.size() == 0) {
            System.out.println("----------------------------------");
            System.out.println("❌ - No hay alquileres que mostrar");
            System.out.println("----------------------------------\n");
        } else {
            for (Alquiler alquiler : alquileres) {
                System.out.println(alquiler);
            }
            System.out.println("\n");
        }
    }

    protected void listarAlquileresCliente() {
        Consola.mostrarCabecera("Listar alquileres de cliente");
        List<Alquiler> alquileres = null;

        try {
            alquileres = controlador.getAlquileres(Consola.leerClienteDni());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (alquileres.size() == 0) {
            System.out.println("-------------------------------------------------------");
            System.out.println("❌ - No hay alquileres a nombre del cliente introducido");
            System.out.println("-------------------------------------------------------\n");
        } else {
            for (Alquiler alquiler : alquileres) {
                System.out.println(alquiler);
            }
            System.out.println("\n");
        }
    }

    protected void listarAlquileresVehiculo() {
        Consola.mostrarCabecera("Listar alquileres de Vehiculo");
        List<Alquiler> alquileres = null;

        try {
            alquileres = controlador.getAlquileres(Consola.leerVehiculoMatricula());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
		}

        if (alquileres.size() == 0) {
            System.out.println("---------------------------------------------------");
            System.out.println("❌ - No hay alquileres para el vehiculo introducido");
            System.out.println("---------------------------------------------------\n");
        } else {
            for (Alquiler alquiler : alquileres) {
                System.out.println(alquiler);
            }
            System.out.println("\n");
        }        
	}
}
