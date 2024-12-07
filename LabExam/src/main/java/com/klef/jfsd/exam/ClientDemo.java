package com.klef.jfsd.exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many clients do you want to add?");
        int numberOfClients = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfClients; i++) {
            System.out.println("Enter details for Client " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Location: ");
            String location = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Mobile Number: ");
            String mobileNumber = scanner.nextLine();

            insertClient(session, name, gender, age, location, email, mobileNumber);
        }

        System.out.println("All Clients in the Database:");
        fetchAllClients(session);

        session.close();
        sessionFactory.close();
        scanner.close();
    }

    public static void insertClient(Session session, String name, String gender, int age, String location, String email, String mobileNumber) {
        Transaction tx = session.beginTransaction();

        Client client = new Client();
        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setLocation(location);
        client.setEmail(email);
        client.setMobileNumber(mobileNumber);

        session.save(client);
        tx.commit();
    }

    public static void fetchAllClients(Session session) {
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
