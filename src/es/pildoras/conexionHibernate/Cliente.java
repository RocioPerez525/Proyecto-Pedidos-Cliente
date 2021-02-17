package es.pildoras.conexionHibernate;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

	public Cliente(String nombre, String apellido, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}

	public Cliente() {
	}
	
	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellidos(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}

	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + "]";
	}
	
	public void agregarPedidos(Pedido elPedido) {
		
		if (pedidos==null) pedidos=new ArrayList<>();
		
		pedidos.add(elPedido);
		
		elPedido.setCliente(this);
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private DetallesCliente detallesCliente;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="direccion")
	private String direccion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Pedido> pedidos;
}
