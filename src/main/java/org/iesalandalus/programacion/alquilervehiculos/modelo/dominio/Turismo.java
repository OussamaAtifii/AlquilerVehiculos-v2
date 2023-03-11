package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo {
	private static int FACTOR_CILINDRADA = 10;
	int cilindrada;

	public Turismo(String marca, String modelo, String matricula, int cilindrada) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
	}

	public Turismo(Turismo turismo) {
		super(turismo);
		setCilindrada(turismo.getCilindrada());
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	protected int getFactorPrecio() {
		return cilindrada / FACTOR_CILINDRADA;
	}

	@Override
	public String toString() {
		return "🚗 " + getMarca() + " " + getModelo() + " (" + cilindrada + " cm3) - " + getMatricula();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
