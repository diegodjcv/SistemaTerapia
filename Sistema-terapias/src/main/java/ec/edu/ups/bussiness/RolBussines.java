package ec.edu.ups.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.RolDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

@Stateless
public class RolBussines {

	
	@Inject
	private RolDAO rolDao;
	
	public Rol readR(String username) {
		Rol aux=rolDao.readRol(username);
		return aux;
	}
	
	
}
