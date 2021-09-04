package ec.edu.ups.view;

import java.io.Serializable; 

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import ec.edu.ups.modelo.Usuario;

@Named("sesion")
@SessionScoped
public class Session implements Serializable{

	public Session() {
		// TODO Auto-generated constructor stub
	}
	
	private Usuario user;
	
	private String usuario;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}