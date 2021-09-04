package ec.edu.ups.bussiness;

import java.util.List; 

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.RegistroLoginDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.modelo.RegistroLogin;

@Stateless
public class RegistroLoginBussiness {

	@Inject
	private RegistroLoginDAO rldao;
	
	public void save(RegistroLogin rl) throws Exception {
		RegistroLogin aux =rldao.read(rl.getId());
		if (aux != null) {
			throw new Exception("RegistroLogin ya guardado.");
		} else {
			rldao.insertN(rl);
		}
	}
	
	
	public int max() {
		return rldao.max();
	}
	
	public List<RegistroLogin> getListadoLogin(Usuario u){
		return rldao.getLoginUsuaio(u);
	}
	
	public void eliminarPorUsuario(Usuario usuario) {
		rldao.removeForUsuario(usuario);
	}
	
}
