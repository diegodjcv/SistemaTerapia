package ec.edu.ups.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
	
	@Id
	private int id;
	
	@NotNull
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion.toUpperCase();
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**Roles:
	 * 1. ADMINISTRADOR
	 * 2. NINIO
	 * 3. TERAPISTA
	 * 
	 */

}
