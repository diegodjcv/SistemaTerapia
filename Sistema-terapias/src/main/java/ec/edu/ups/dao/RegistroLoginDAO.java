package ec.edu.ups.dao;

import java.util.List; 

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.modelo.RegistroLogin;

@Stateless
public class RegistroLoginDAO {

	@Inject
	private EntityManager em;
	
	
	public void insertN(RegistroLogin rl) {
		em.persist(rl); 
	}
	
	public void remove(int num) {
		em.remove(read(num));
	}

	
	public  List<RegistroLogin> getRegistroLoginU(){
		String jsql = "SELECT rl FROM RegistroLogin rl ";
		Query query=em.createQuery(jsql, RegistroLogin.class);
		
		List <RegistroLogin> lista=query.getResultList();
		return lista;
	}
	
	public RegistroLogin read(int id) {
		RegistroLogin aux=em.find(RegistroLogin.class, id);
		System.out.println("Registro con codigo: "+id);
		return aux;
	}
	
	public List<RegistroLogin> getLoginUsuaio(Usuario usuario) {
		String jsql = "SELECT rl FROM RegistroLogin rl WHERE rl.usuario = :usuario";
		Query query=em.createQuery(jsql, RegistroLogin.class);
		query.setParameter("usuario", usuario);
		List <RegistroLogin> lista=query.getResultList();
		return lista;
	}
	
	
	public int max() {
		String jsql = "SELECT rl FROM RegistroLogin rl";
		Query query=em.createQuery(jsql, RegistroLogin.class);
		List <RegistroLogin> lista=query.getResultList();
		int maximo=lista.size();
		if(maximo==0) {
			maximo=1;
		}else {
			maximo=lista.get(lista.size()-1).getId();
			maximo=maximo+1;
		}
		System.out.println("SIZE LIST: "+lista.size()+" MAXIMO: "+maximo);
		return maximo;
	}
	
	public void removeForUsuario(Usuario usuario) {
		String jsql = "SELECT rl FROM RegistroLogin rl WHERE rl.usuario = :usuario";
		Query query=em.createQuery(jsql, RegistroLogin.class);
		query.setParameter("usuario", usuario);
		List <RegistroLogin> lista=query.getResultList();
		for(int i=0;i<lista.size();i++) {
			RegistroLogin r=lista.get(i);
			remove(r.getId());
		}
	}
}
