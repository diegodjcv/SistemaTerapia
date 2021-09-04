package ec.edu.ups.view;

import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import ec.edu.ups.bussiness.UsuarioBussiness;
import ec.edu.ups.bussiness.RegistroLoginBussiness;
import ec.edu.ups.bussiness.RolBussines;
import ec.edu.ups.modelo.RegistroLogin;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private boolean activo;
	private boolean inactivo;
	
	private GestionCorreo correo;
	
	private String cedula;
	
	private String fechaNac;
	
	@Inject
	private UsuarioBussiness usBussiness;
	
	
	private Usuario newUsuario;
	
	@Inject
	private RegistroLoginBussiness rlbuss;
	
	@Inject
	private RolBussines rolbuss;
	
	private RegistroLogin registro;
	private Rol rol;
	@Inject
	private Session sesion;
	
	private Usuario usuario;
	
	private List<Usuario> ninios; 
	
	private List<Usuario> terapistas; 
	
	
	@PostConstruct
	public void intit() {
		activo=true;
		inactivo=false;
		newUsuario =new Usuario();
		registro=new RegistroLogin();
		ninios =new ArrayList<>();
		ninios = usBussiness.getListadoNinios();
		terapistas = usBussiness.getListadoTerapistas();
		rol = new Rol();
		usuario =new Usuario();
		correo=new GestionCorreo();
		
	}
	
	
	public String iniciarSesion() {
		String res = "";
		registro.setId(rlbuss.max());
		Date fechaRegistro = new Date();
		
		registro.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fechaRegistro));
		registro.setHora(fechaRegistro.getHours() + ":" + fechaRegistro.getMinutes() + ":" + fechaRegistro.getSeconds());
		Usuario u = new Usuario();
		u = usBussiness.readU(newUsuario.getUsername());
		if (u == null) {
			res = "loginFallido?faces-redirect=true";
		} else {
			registro.setUsuario(u);
			//u = usuarioDao.buscarLog(newUsuario.getUsuario(), newUsuario.getPassword());
			if (u != null) {
				String p1=newUsuario.getPassword();
				String p2=u.getPassword();
				System.out.println("PASSWORD_In: "+newUsuario.getPassword()+" PASSWORD_Bas: "+u.getPassword());
				if (u.getPassword().toString().equals(newUsuario.getPassword().toString())) {
					HttpSession Session = Util.getSession();
					Session.setAttribute("username", this.newUsuario);
					sesion.setUser(newUsuario);
					registro.setEstado("CORRECTO");
					try {
						rlbuss.save(registro);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//correo.notificacionLogin(registro, u);
					if (u.getRol().getDescripcion().toUpperCase().equals("ADMINISTRADOR")) {
						System.out.println("redireccion admin");
						res = "menuAdministrador?faces-redirect=true";
					} else {
						if (u.getRol().getDescripcion().toUpperCase().equals("TERAPISTA")) {
							System.out.println("redireccion terapista");
							res = "menuTerapista?faces-redirect=true&cedula=" + u.getCedula();
						}else {
							if (u.getRol().getDescripcion().toUpperCase().equals("NINIO")) {
								System.out.println("redireccion ninio");
								res = "menuNinio?faces-redirect=true";
							}
						}
					}
				}else {
					registro.setEstado("FALLIDO");
					res = "loginFallido?faces-redirect=true";
					try {
						rlbuss.save(registro);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return res;
	}
	
	public String guardarNinio() {
		try {
			rol=rolbuss.readR("NIÑO");
			System.out.println("USUARIO INGRESADO CON ROL "+rol.getDescripcion()+" EMAIL "+newUsuario.getEmail());
			if(correo.validarCorreo(newUsuario.getEmail())) {
				newUsuario.setRol(rol);
				usBussiness.save(newUsuario);
				correo.notificacionUsuarioNuevo(newUsuario);
				return "list-ninios?faces-redirect=true";
			}else {
				return "emailErroneo?faces-redirect=true";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			System.out.println("ERROR AL GUARDAR");
			e.printStackTrace();
		}
		return null;
	}

	public String guardarTerapista() {
		try {
			rol=rolbuss.readR("TERAPISTA");
			
			if(correo.validarCorreo(newUsuario.getEmail())) {
				newUsuario.setRol(rol);
				usBussiness.save(newUsuario);
				correo.notificacionUsuarioNuevo(newUsuario);
				return "list-terapistas?faces-redirect=true";
			}else {
				return "emailErroneo?faces-redirect=true";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			System.out.println("ERROR AL GUARDAR");
			e.printStackTrace();
		}
		return null;
	}
	
	public void convertirFecha() {
		
	}
	
	
	public String eliminar(String ced) {
		try {
			Usuario us=new Usuario();
			us=usBussiness.readUs(ced);
			if(us.getRol().getDescripcion().toUpperCase().equals("NIÑO")) { 
				
				usBussiness.eliminar(ced);
				System.out.println("Registro eliminado");
			}else {
				if(us.getRol().getDescripcion().toUpperCase().equals("TERAPISTA")) {
					rlbuss.eliminarPorUsuario(us);
					usBussiness.eliminar(ced);
					System.out.println("Registro eliminado");
				}
			}
			return "list-ninios?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();			
			
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					e.getMessage(), "Error");
		}		
		return null;
	}
	
	public void generarUs() {
		String[] newAp = newUsuario.getApellidos().split("\\s+");
		String letra1=newUsuario.getNombres().toString().charAt(0)+"";
		int num=(int) (Math.random()*(999-100)+100);
		String password=String.valueOf((int) (Math.random()*(99999999-10000000)+10000000));
		String username=letra1.toLowerCase().concat(newAp[0].toLowerCase())+num;
		newUsuario.setUsername(username);
		newUsuario.setPassword(password);
		activo=false;
		inactivo=true;
	}
	
	public void loadData() {
		System.out.println("load data " + cedula);
		usuario=usBussiness.readUs(cedula);
	}
	
	
	
	
	
	
	


	

	public Usuario getNewUsuario() {
		return newUsuario;
	}
	public void setNewNinio(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}


	public List<Usuario> getNinios() {
		return ninios;
	}


	public void setNinios(List<Usuario> ninios) {
		this.ninios = ninios;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public boolean isInactivo() {
		return inactivo;
	}


	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
	}


	public List<Usuario> getTerapistas() {
		return terapistas;
	}


	public void setTerapistas(List<Usuario> terapistas) {
		this.terapistas = terapistas;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fechaNac);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        newUsuario.setFechaNac(fechaDate);
		
	}
	
	
	
	
	
}
