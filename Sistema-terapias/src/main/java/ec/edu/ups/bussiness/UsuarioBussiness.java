package ec.edu.ups.bussiness;

import java.util.List; 

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;


@Stateless
public class UsuarioBussiness {
	
	@Inject
	private UsuarioDAO usuarioDao;

	
	public void save(Usuario u) throws Exception {
		Usuario aux = usuarioDao.read(u.getCedula());
		if (aux != null) {
			throw new Exception("Niño ya registrada.");
		} else {
			usuarioDao.insert(u);
		}
	}
	
	public Usuario readU(String username) {
		Usuario aux=usuarioDao.readNinio(username);
		return aux;
	}
	
	public Usuario readUs(String cedula) {
		Usuario aux=usuarioDao.read(cedula);
		return aux;
	}
	public void actualizar(Usuario Ninio) throws Exception {
		Usuario aux = usuarioDao.read(Ninio.getCedula());
		if(aux==null)
			throw new Exception("Cuenta no existe");
		else
			usuarioDao.update(Ninio);
	}
	
	public List<Usuario> getListadoNinios(){
		return usuarioDao.getNiniosList();
	}
	
	public List<Usuario> getListadoTerapistas(){
		return usuarioDao.getTerapistasList();
	}
	
	public void eliminar(String ced) throws Exception {
		Usuario aux = usuarioDao.read(ced);
		if(aux==null)
			throw new Exception("Usuario no existe");
		else
			usuarioDao.remove(ced);
	}
	
	
	//hola soy yo
	
	
}
