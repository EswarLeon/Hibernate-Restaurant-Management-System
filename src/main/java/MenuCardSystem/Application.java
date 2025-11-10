package MenuCardSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Application {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("---------------");
		EntityManager em = HibernateUtil.getEntityManager();
		seedmenu(em);
		boolean loop = true;
//		while(loop) {
			System.out.println("\n----------------------------------------\nPress 1 to Show Menu");
			System.out.println("Press 2 to Order items");
			System.out.println("Press 3 to Add new items");
			System.out.println("Press 4 to View Orders By Customer Name");
			System.out.println("Press 5 to View History of Orders");
			System.out.println("Press 6 to Exit");
			System.out.println("----------------------------------------");
			System.out.print("Enter Here : ");
			int choice = scan.nextInt();
			switch(choice) {
			case 1:
				showmenu(em);
				break;
			case 2:
				orderitems(em);
				break;
			case 3:
				addnewitems(em);
				break;
			case 4:
				vieworderbycustomer(em);
				break;
			case 5:
				historyoforders(em);
				break;
			case 6:
				System.out.println("\n\n-----Thank you-----\n\n");
				loop = false;
			}
//		}
		em.close();
	}
	private static void historyoforders(EntityManager em) {
		em.getTransaction().begin();
		List<Order> orders = em.createQuery("from Order",Order.class).getResultList();
		em.getTransaction().commit();
		String temp = "";
		double total = 0.0;
		for(Order o : orders) {
			String temp2 = o.getCustomername();
		    if(!temp2.equals(temp)) {
		        	if(!temp.isEmpty()) {
		        		System.out.println("Total: " + total + "\n");
		        	}
		        temp = temp2;
		        total = 0.0;
		        System.out.println("\n" + o.getCustomername()+" ("+o.getTime()+")\n---------------");
		    }
		    System.out.println(o);
		    total += o.getPrice() * o.getQuantity();
		}
		if(!temp.isEmpty()) {
		    System.out.println("Total: " + total + "\n");
		}
		
	}
	private static void vieworderbycustomer(EntityManager em) {
		scan.nextLine();
		System.out.print("Enter Name : ");
		String name = scan.nextLine();
		List<Order> view = em.createQuery("Select o from Order o where o.customername = :name",Order.class).setParameter("name",name).getResultList();
		if(view.isEmpty()) {
			System.out.println("No One is Found");
		}
		else {
			System.out.println(name+"\n---------------");
			double total = 0.0;
			for(Order o : view) {
				System.out.println(o+"("+o.getTime()+")");
				total+=o.getPrice();
			}
			System.out.println("Total : "+total);
		}
	}
	private static void addnewitems(EntityManager em) {
		scan.nextLine();
		System.out.print("Enter Item Name : ");
		String itemname = scan.nextLine();
		System.out.print("Enter Price : ");
		double itemprice = scan.nextDouble();
		em.getTransaction().begin();
		em.persist(new item(itemname,itemprice));
		em.getTransaction().commit();
		System.out.println("Item Added...");
	}
	private static void orderitems(EntityManager em) {
		LocalDateTime time = null;
		showmenu(em);
		double total = 0.0;
		List<Order> order = new ArrayList<>();
		scan.nextLine();
		System.out.print("Enter Your Name : ");
		String name = scan.nextLine();
		showmenu(em);
		while(true) {
			System.out.print("\nEnter The item id to Proceed Order \nor 0 to Finish  : ");
			int id = scan.nextInt();
			if(id == 0) {
				break;
			}
			else {
				item Item = em.find(item.class,id);
				System.out.print("Enter "+Item.getName()+" Quantity : ");
				int quantity = scan.nextInt();
				order.add(new Order(name,Item.getName(), quantity, Item.getPrice(),LocalDateTime.now()));
				total += Item.getPrice()*quantity;
				System.out.println("------------------------------------------");
				System.out.printf("%-5s %-20s %10s\n","NO","SELECTED ITEMS","PRICE");
				System.out.println("------------------------------------------");
				int i = 1;
				for(Order o : order) {
					System.out.printf("%-5d %-20s %10.2f\n",i++,o.getItem()+" x"+o.getQuantity(),o.getPrice()*o.getQuantity());
				}
				System.out.println("------------------------------------------");
				System.out.println("Total : "+total);
				System.out.println("------------------------------------------");
			}
		}
		for(Order o : order) {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		}
		System.out.println("------------------------------------------");
		System.out.printf("%-5s %-20s %10s\n","NO","SELECTED ITEMS","PRICE");
		System.out.println("------------------------------------------");
		int i = 1;
		for(Order o : order) {
			System.out.printf("%-5d %-20s %10.2f\n",i++,o.getItem(),o.getPrice());
		}
		System.out.println("------------------------------------------");
		System.out.println("Total : "+total);
		System.out.println("------------------------------------------");
		System.out.println("Orders Saved in Database...");
	}
	private static void seedmenu(EntityManager em) {
		 List<item> existing= em.createQuery("from item",item.class).getResultList();
		 if(existing.isEmpty()) {
			 EntityTransaction et = em.getTransaction();
			 et.begin();
			 em.persist(new item("Tea",30));
			 em.persist(new item("Coffee",40));
			 em.persist(new item("Veg Puff",50));
			 em.persist(new item("Chicken Puff",70));
			 em.persist(new item("Lemon Tea",25));
			 et.commit();
			 System.out.println("Default Menu Items Added");
		 }
	}
	private static void showmenu(EntityManager em) {
		List<item> menu = em.createQuery("from item",item.class).getResultList();
		System.out.println("\n------------------------------------------");
		System.out.printf("%-5s %-20s %10s\n", "ID", "ITEM NAME", "PRICE");
		System.out.println("------------------------------------------");

		for (item i : menu) {
		    System.out.printf("%-5d %-20s %10.2f\n", i.getId(), i.getName(), i.getPrice());
		}

		System.out.println("------------------------------------------");
	}

}
