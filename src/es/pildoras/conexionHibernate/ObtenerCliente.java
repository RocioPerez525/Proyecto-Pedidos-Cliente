package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				   .addAnnotatedClass(Cliente.class)
				   .addAnnotatedClass(DetallesCliente.class)
				   .buildSessionFactory();

		Session miSession = miFactory.openSession();

		try {
		
			miSession.beginTransaction();
			
			//Obtener DetallesCliente
			
			DetallesCliente detallesDeCliente = miSession.get(DetallesCliente.class, 3);
			
			System.out.println (detallesDeCliente);
			
			System.out.println(detallesDeCliente.getElCliente());
			
			System.out.println("Ahora vamos a eliminar en cascada");
			
			miSession.delete(detallesDeCliente);
			
			miSession.getTransaction().commit();
			
			
		}	
			catch(Exception ex1){
				
				ex1.printStackTrace();
				
			}
		
		finally{
		
			miSession.close();
			
			miFactory.close();
		}
	 
	}
	
}
