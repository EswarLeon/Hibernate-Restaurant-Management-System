package MenuCardSystem;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Removerow {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		EntityManager em = HibernateUtil.getEntityManager();
		System.out.print("Enter id : ");
		int id = scan.nextInt();
		em.getTransaction().begin();
		Order order = em.find(Order.class, id);
		em.remove(order);
		em.getTransaction().commit();
	}
}
