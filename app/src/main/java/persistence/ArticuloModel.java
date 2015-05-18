package persistence;

import android.util.Log;

public class ArticuloModel {

	private int idarticulo;
	private String nombre;
	private int precio;
	private String descripcion;
	private String ideamil;
	private String fechapublicacion;
	private String nickname;
	private String tipoproducto;
	private String subcategoria;
	private String categoria;
	private int reputacion;
	private String estado;
	private String imagenmuestra;
	
	public ArticuloModel(int idarticulo, String nombre, int precio,
			String descripcion, String ideamil, String fechapublicacion,
			String nickname, String tipoproducto, String subcategoria,
			String categoria, int reputacion, String estado, String imagenmuestra) {
		this.idarticulo = idarticulo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.ideamil = ideamil;
		this.fechapublicacion = fechapublicacion;
		this.nickname = nickname;
		this.tipoproducto = tipoproducto;
		this.subcategoria = subcategoria;
		this.categoria = categoria;
		this.reputacion = reputacion;
		this.estado = estado;
		this.imagenmuestra = imagenmuestra;
		
		Log.e("articulo", estado);
	}

	public int getIdarticulo() {Log.e("articulo", String.valueOf(idarticulo));
		return idarticulo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getIdeamil() {
		return ideamil;
	}

	public String getFechapublicacion() {
		return fechapublicacion;
	}

	public String getNickname() {
		return nickname;
	}

	public String getTipoproducto() {
		return tipoproducto;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public int getReputacion() {
		return reputacion;
	}

	public String getEstado() {
		return estado;
	}
	
	public String getImagenMuestra(){
		return imagenmuestra;
	}
	
	
	
	
	
}
