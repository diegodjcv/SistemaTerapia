package ec.edu.ups.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

public class UsuarioDAO {

	@Inject
	private EntityManager em;
	
	@Inject
	private RolDAO rdao;
	
	public void insert(Usuario n) {
		em.persist(n);
	}
	
	
	public void update(Usuario n) {
		em.merge(n);
	}
	
	public void remove (String ced) {
		em.remove(read(ced));
	}
	
	public Usuario read(String cedula) {
		Usuario aux=em.find(Usuario.class, cedula);
		System.out.println("USUARIO CON CEDULA: "+cedula);
		return aux;
	}
	
	public Usuario readNinio(String username) {
		Usuario n=null;
		try {
			String jsql = "SELECT u FROM Usuario u WHERE u.username=:username";
			Query query=em.createQuery(jsql, Usuario.class);
			query.setParameter("username", username);
			n = (Usuario) query.getSingleResult();
			System.out.println(n.getCedula()+" "+n.getNombres()+" "+n.getApellidos()+" "+n.getUsername());
		}catch(NoResultException e) {
			n=null;
		}
		
		return n;
	}

	
	public List<Usuario> getNiniosList() {
		Usuario us=null;
		Rol r= new Rol();
		r=rdao.readRol("NIÑO");
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.rol=:rol" , Usuario.class); 
		query.setParameter("rol", r);
		List <Usuario> lista=query.getResultList();
		return lista;
	}
	
	public List<Usuario> getTerapistasList() {
		Usuario us=null;
		Rol r= new Rol();
		r=rdao.readRol("TERAPISTA");
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.rol=:rol" , Usuario.class); 
		query.setParameter("rol", r);
		List <Usuario> lista=query.getResultList();
		return lista;
	}
	
	
	public Usuario buscarLog(String username, String contrasenia) {
		Usuario n=null;
		try {
		Query query = em.createQuery("SELECT n FROM Ninio n WHERE n.username = :username AND n.password = :contrasenia" , Usuario.class); 
		query.setParameter("username", username);
		query.setParameter("password", contrasenia);
		n = (Usuario) query.getSingleResult();
	    System.out.println("Usuario Encontrado "+n.getNombres()+" "+n.getPassword()+"="+contrasenia);
	    if(n!=null) {
	    	return n;
	    }
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("NO EXISTE USUARIO CON EL USERNAME INGRESADO");
		}
		return n;
	}
	
	
	
	
}
