package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import com.pluralsight.NorthwindTradersSpringBoot.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);

/*        SimpleProductDAO simpleProductDAO = new SimpleProductDAO();
        ProductService ProductService = new ProductService(simpleProductDAO);*/

		ProductService ProductService = context.getBean(ProductService.class);

		Scanner scanner = new Scanner(System.in); // Scanner for reading user input.
		int choice;
		do {
			// Displaying the menu options to the user.
			System.out.println("========== Ledger Application ==========");
			System.out.println("1. List Products");
			System.out.println("2. Add Product");
			System.out.println("3. Update Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Search Product");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consumes the newline character after the integer input.

			// Handling user's choice with switch-case.
			switch (choice) {
				case 1:
					listProducts(ProductService);
					break;
				case 2:
					addProduct(scanner, ProductService);
					break;
				case 3:
					updateProduct(scanner, ProductService);
					break;
				case 4:
					deleteProduct(scanner, ProductService);
					break;
				case 5:
					searchProduct(scanner, ProductService);
					break;
				case 0:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0); // Repeat until user chooses to exit.

		scanner.close(); // Closing the scanner resource.
	}

	private static void listProducts(ProductService ProductService) {
		// This method retrieves and displays a list of all Products.
		System.out.println("========== List of Products ==========");
		List<Product> Products = ProductService.getAllProducts();
		for (Product Product : Products) {
			System.out.println(Product); // Prints each Product.
		}
		System.out.println(); // Adds an empty line for better readability.
	}

	private static void addProduct(Scanner scanner, ProductService ProductService) {
		// This method adds a new Product based on user input.
		System.out.println("Enter Product name:");
		String name = scanner.nextLine();
		System.out.print("Enter category id: ");
		int categoryId = scanner.nextInt();
		scanner.nextLine(); // Consumes the newline character.
		System.out.print("Enter unit price: ");
		double unitPrice = scanner.nextDouble();
		scanner.nextLine();

		Product Product = new Product(name, categoryId, unitPrice);
		Product newProduct = ProductService.addProduct(Product); // Adds the Product to the service.

		System.out.println("Product added successfully.\n");
		System.out.println(newProduct);
		System.out.println();
	}

	private static void updateProduct(Scanner scanner, ProductService ProductService) {
		// This method updates an existing Product based on user input.
		System.out.print("Enter the Product ID to update: ");
		int ProductId = scanner.nextInt();
		scanner.nextLine(); // Consumes the newline character.

		Product existingProduct = ProductService.getProductById(ProductId);
		if (existingProduct == null) {
			System.out.println("Product not found.\n");
			return;
		}

		System.out.println("Enter Product name:");
		String name = scanner.nextLine();
		System.out.print("Enter category id: ");
		int categoryId = scanner.nextInt();
		scanner.nextLine(); // Consumes the newline character.
		System.out.print("Enter unit price: ");
		double unitPrice = scanner.nextDouble();
		scanner.nextLine();

		Product updatedProduct = new Product(name, categoryId, unitPrice);
		ProductService.updateProduct(ProductId, updatedProduct); // Updates the Product.

		System.out.println("Product updated successfully.\n");
	}

	private static void deleteProduct(Scanner scanner, ProductService ProductService) {
		// This method deletes a Product based on the Product ID provided by the user.
		System.out.print("Enter the Product ID to delete: ");
		int ProductId = scanner.nextInt();
		scanner.nextLine(); // Consumes the newline character.

		Product existingProduct = ProductService.getProductById(ProductId);
		if (existingProduct == null) {
			System.out.println("Product not found.\n");
			return;
		}

		ProductService.deleteProduct(ProductId); // Deletes the Product.

		System.out.println("Product deleted successfully.\n");
	}

	private static void searchProduct(Scanner scanner, ProductService ProductService) {
		// This method searches for a Product based on the Product ID provided by the user.
		System.out.print("Enter the Product ID to search: ");
		int ProductId = scanner.nextInt();
		scanner.nextLine(); // Consumes the newline character.

		Product Product = ProductService.getProductById(ProductId);
		if (Product == null) {
			System.out.println("Product not found.\n");
		} else {
			System.out.println("========== Product Details ==========");
			System.out.println(Product); // Displays the details of the found Product.
			System.out.println();
		}
	}

}