package com.example;

import java.io.*;
import java.util.*;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return name + "," + phone + "," + email;
    }

    public static Contact fromString(String line) {
        String[] parts = line.split(",");
        return new Contact(parts[0], parts[1], parts[2]);
    }
}

public class ContactManager {
    static List<Contact> contactList = new ArrayList<>();
    static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        loadContacts();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Contact Manager ===");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addContact(scanner);
                case 2 -> viewContacts();
                case 3 -> editContact(scanner);
                case 4 -> deleteContact(scanner);
                case 5 -> saveContacts();
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Add a new contact
    static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        contactList.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully.");
    }

    // View all contacts
    static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contactList.size(); i++) {
            Contact c = contactList.get(i);
            System.out.println((i + 1) + ". Name: " + c.name + ", Phone: " + c.phone + ", Email: " + c.email);
        }
    }

    // Edit contact
    static void editContact(Scanner scanner) {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter contact number to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < contactList.size()) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            contactList.set(index, new Contact(name, phone, email));
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    // Delete contact
    static void deleteContact(Scanner scanner) {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter contact number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < contactList.size()) {
            contactList.remove(index);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    // Load contacts from file
    static void loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contactList.add(Contact.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    // Save contacts to file
    static void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contactList) {
                writer.write(c.toString());
                writer.newLine();
            }
            System.out.println("Contacts saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }
}
