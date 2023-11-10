package pyfinal;

public abstract class producto {
	private int codigo;
	private String descripcion;
	private String tipo;
	private int cantidad;
	private float precio;
	public producto(int codigo, String descripcion, String tipo, int cantidad, float precio) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
}
