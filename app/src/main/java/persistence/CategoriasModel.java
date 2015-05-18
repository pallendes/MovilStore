package persistence;

public class CategoriasModel{
	
	private int idcategoria;
	private String nombre;
	
	public CategoriasModel(int idcategoria, String nombre) {
		super();
		this.idcategoria = idcategoria;
		this.nombre = nombre;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
