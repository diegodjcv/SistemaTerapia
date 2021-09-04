package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario {

	@Id
	@Column(unique = true)
	@NotNull
	private String cedula;
	
	@NotNull
	private String nombres;
	
	@NotNull
	private String apellidos;
	
	@NotNull
	private Date fechaNac;
	
	@Email
	private String email;
	
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotNull
	private String password;
	
	@OneToOne
	private Rol rol;
	
	@OneToMany(mappedBy="ninio")
    private List<Reporte> reportes;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres.toUpperCase();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos.toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Reporte> getReportes() {
		return reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

	public Date getFechaNac() {
		
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int edad() {
		int ed=0;
		Date fechaActual =new Date();
		ed=fechaActual.getYear()-this.getFechaNac().getYear();
		return ed;
	}
	
	
}
