package persistence;

public class SubCategoriasModel {
	
	private int idsubcat;
	private String nombre;
	
	public SubCategoriasModel(int idsubcat, String nombre) {
		this.idsubcat = idsubcat;
		this.nombre = nombre;
	}

	public int getIdsubcat() {
		return idsubcat;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
