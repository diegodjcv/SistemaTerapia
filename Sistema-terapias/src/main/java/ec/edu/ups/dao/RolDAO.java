package ec.edu.ups.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

public class RolDAO {

	@Inject
	private EntityManager em;
	
	
	public  List<Rol> getRoles(){
		String jsql = "SELECT r FROM Rol r";
		Query query=em.createQuery(jsql, Rol.class);
		
		List <Rol> lista=query.getResultList();
		return lista;
	}
	
	public Rol readRol(String nombre) {
		Rol r=new Rol();
		try {
			String jsql = "SELECT r FROM Rol r WHERE r.descripcion=:desc";
			Query query=em.createQuery(jsql, Rol.class);
			query.setParameter("desc", nombre.toUpperCase());
			r = (Rol) query.getSingleResult();
		}catch(NoResultException e) {
			r=null;
		}
		
		return r;
	}
	
	
}
