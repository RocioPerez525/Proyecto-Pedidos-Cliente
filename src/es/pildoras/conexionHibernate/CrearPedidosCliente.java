package es.pildoras.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrearPedidosCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
								   .addAnnotatedClass(Cliente.class)
								   .addAnnotatedClass(DetallesCliente.class)
								   .addAnnotatedClass(Pedido.class)
								   .buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			miSession.beginTransaction();
			
			//obtener el cliente de la tabla cliente de la bbdd
			Cliente elCliente = miSession.get(Cliente.class, 5);
			
			//crear pedidos del cliente
			Pedido pedido1 = new Pedido(new GregorianCalendar(2020,7,5));
			Pedido pedido2 = new Pedido(new GregorianCalendar(2020,6,15));
			Pedido pedido3 = new Pedido(new GregorianCalendar(2020,4,25));
			
			//agregar pedidos creados al cliente creado
			elCliente.agregarPedidos(pedido1);
			elCliente.agregarPedidos(pedido2);
			elCliente.agregarPedidos(pedido3);
			
			//guardar los pedidos en la bbdd en la tabla pedido
			miSession.save(pedido1);
			miSession.save(pedido2);
			miSession.save(pedido3);
			
			miSession.getTransaction().commit();
			
			System.out.println("Registros insertados correctamente en BBDD");
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			miSession.close();
			miFactory.close();
		}
	}

}
