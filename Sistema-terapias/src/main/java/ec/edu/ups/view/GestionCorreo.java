package ec.edu.ups.view;

import java.util.List;  
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ec.edu.ups.modelo.Usuario;

public class GestionCorreo {

	Properties p;
	String correo="diegodjcv@gmail.com";
	String password="kuenkitados";  
	
	
	 public GestionCorreo() {
		 p = new Properties();
		 p.put("mail.smtp.host", "smtp.gmail.com");
         p.setProperty("mail.smtp.starttls.enable", "true");
         p.setProperty("mail.smtp.port", "587");
         p.setProperty("mail.smtp.user",correo);
         p.setProperty("mail.smtp.ath", "true");
         Session s=Session.getDefaultInstance(p,null);
	}


/*
	 public boolean notificacionCuentaNueva(Usuario us, CuentaAhorro ca)
	    {
	        try{
	        	String mensaje="Acaba de crear una cuenta en el Sistema Bancario A.G. \n"
	        			+"Username: "+us.getUsuario()+"\n"
	        			+"Password: "+us.getPassword()+"\n"
	        			+"CUENTA:"+"\n"
	        			+"		Numero de cuenta: "+ca.getNumeroCuenta()+"\n"
	        			+"		Fecha de aertura: "+ca.getFechaApertura()+"\n"
	        			+"Puede ingresar a su cuenta en el sistema bancario mediante el usuario y contraseña asignados realizar depositos, retiro, consultas.";
	            Session s=Session.getDefaultInstance(p,null);
	            String destino=us.getEmail();
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(mensaje);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Creacion de cuenta en Sistema Bancario A.G.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e);
	            System.out.println(e);
	            return false;
	        }
	    }
	    */
	 
	 public boolean notificacionUsuarioNuevo(Usuario us)
	    {
	        try{
	        	String mensaje="Se acaba de crear una cuenta de usuario en el Sistema de Terapias de Lenguaje \n"
	        			+"Username: "+us.getUsername()+"\n"
	        			+"Password: "+us.getPassword()+"\n"
	        			+"Puede ingresar a su cuenta en el sistema mediante el usuario y contraseña asignados.";
	            Session s=Session.getDefaultInstance(p,null);
	            String destino=us.getEmail();
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(mensaje);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Creacion de una cuenta de usuario en el Sistema de Terapias de Lenguaje.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e);
	            System.out.println(e.toString());
	            return false;
	        }
	    }
	 
	 /*
	 public boolean notificacionLogin( RegistroLogin rl, Usuario us)
	    {
	        try{
	        	String destino=us.getEmail();
	        	String mensaje="Acaba de iniciarse sesion en el Sistema Bancario A.G. con la cuenta: "+"\n"
	        			+"USUARIO: "+"\n"
	        			+"		Cedula: "+us.getCedula()+"\n"
	        			+"		Nombre: "+us.getNombres()+" "+us.getApellidos()+"\n"
	        			+"		Username: "+us.getUsuario()+"\n"
	        			+"El login fue "+rl.getEstado()+" se realizó en la fecha: "+rl.getFecha()+" a las "+rl.getHora()+" ";
	        	//System.out.println(mensaje);
	            Session s=Session.getDefaultInstance(p,null);
	            
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(mensaje);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Notificacion inicio de sesión Sistema Bancario A.G.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e.getLocalizedMessage());
	            return false;
	        }
	    }
	    */
	    public boolean validarCorreo(String email)
	    {
	        String[] parts = email.split("@");
	        String part2 = parts[1];
	        if(part2.equals("gmail.com") || part2.equals("hotmail.com"))
	        {
	            return true;
	        }else{
	            return false;
	        }
	    }
	/*
	    public boolean aprobadoCredito(SolicitudCredito sc, List<CuotaCredito> cuotas, Usuario us) {
	    	try{
	        	String destino=us.getEmail();
	        	String mensaje="Acaba de ser APROBADO su crédito: "+"\n"
	        			+"Valor del credito es de $"+ sc.getMonto()+" a "+sc.getPlazo()+" meses plazo"+"\n"
	        			+"USUARIO: "+"\n"
	        			+"		Cedula: "+us.getCedula()+"\n"
	        			+"		Nombre: "+us.getNombres()+" "+us.getApellidos()+"\n"
	        			+"TABLA DE AMORTIZACION "+"\n";
	        	String cuot="|_#_|________F E C H A________|_V A L O R_|"+"\n";
	        	for(int i=0;i<cuotas.size();i++) {
	        		CuotaCredito cc=new CuotaCredito();
	        		cc=cuotas.get(i);
	        		cuot=cuot+"| "+(i+1)+" | "+cc.getFecha()+" | $"+cc.getValor()+" |"+"\n";
	        	}
	        	String resultado=mensaje.concat(cuot);
	        	
	            Session s=Session.getDefaultInstance(p,null);
	            
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(resultado);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Notificacion de solicitud de credito Sistema Bancario A.G.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e.getLocalizedMessage());
	            return false;
	        }
	    }
	    
	    public boolean rechazoCredito(String motivo, Usuario us) {
	    	try{
	        	String destino=us.getEmail();
	        	String mensaje="Acaba de ser RECHAZADO su crédito debido a:  "+"\n"
	        			+motivo+"\n"
	        			+"USUARIO: "+"\n"
	        			+"		Cedula: "+us.getCedula()+"\n"
	        			+"		Nombre: "+us.getNombres()+" "+us.getApellidos()+"\n";
	            Session s=Session.getDefaultInstance(p,null);
	            
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(mensaje);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Notificacion de solicitud de credito Sistema Bancario A.G.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e.getLocalizedMessage());
	            return false;
	        }
	    }
	    */
	    public boolean notificacionContrasenia(Usuario us) {
	    	try{
	        	String destino=us.getEmail();
	        	String mensaje="El usuario "+us.getUsername()+" solicitó cambio de contraseña. "+"\n"
	        			+"Su nueva contraseña es: "
	        			+us.getPassword()+"\n";
	            Session s=Session.getDefaultInstance(p,null);
	            
	            BodyPart texto=new MimeBodyPart();
	            texto.setText(mensaje);
	            
	            MimeMultipart m=new MimeMultipart();
	            m.addBodyPart(texto);
	            
	            MimeMessage mens=new MimeMessage(s);
	            mens.setFrom(new InternetAddress(correo));
	            mens.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            mens.setSubject("Cambio de contrasenia de usuario del Sistema Bancario A.G.");
	            mens.setContent(m);
	            
	            Transport t=s.getTransport("smtp");
	            t.connect(correo,password);
	            t.sendMessage(mens, mens.getAllRecipients());
	            t.close();
	            return true;
	        }catch(Exception e)
	        {
	            System.out.println("ERROR!! " +e.getLocalizedMessage());
	            return false;
	        }
	    }
}
