package laboral;

public class Empleado extends Persona{

	
	private int categoria;
	public int anyos;
	
	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
		
	}

	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		
		if(categoria>0 && categoria < 11) {
			this.categoria = categoria;
		}else {
			throw new DatosNoCorrectosException("Categoria erronea");
		}
		
		if(anyos >=0) {
			this.anyos = anyos;
		}else {
			throw new DatosNoCorrectosException("Edad erronea");
		}
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if(categoria>0 && categoria < 11) {
			this.categoria = categoria;
		}else {
			throw new DatosNoCorrectosException("Categoria erronea");
		}
	}
	
	public void incrAnyo() {
		this.anyos++;
	}

	@Override
	public String toString() {
		return "Empleado [categoria=" + categoria + ", anyos=" + anyos + ", nombre=" + nombre + ", dni=" + dni
				+ ", sexo=" + sexo + "]";
	}
	
	
	
}
