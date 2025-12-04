package com.pluralsight.NorthwindTradersSpringBoot.services;

import com.pluralsight.NorthwindTradersSpringBoot.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProductService provides a layer of abstraction between the controller and the data access object (DAO).
 * It contains the business logic to interact with Products and uses the IProductDAO for database operations.
 */
@Component
// Indicates that this class is a Spring component. It will be automatically detected and instantiated by Spring.
public class ProductService {
    private IProductDAO ProductDAO; // Interface to data access object for Products.

    /**
     * Autowired constructor for dependency injection.
     * Spring will inject an implementation of IProductDAO when ProductService is created.
     *
     * @param ProductDAO the DAO implementation to be used by this service.
     */
    @Autowired // Injects the ProductDAO implementation.
    public ProductService(IProductDAO ProductDAO) {
        this.ProductDAO = ProductDAO;
    }

    /**
     * Adds a new Product.
     *
     * @param Product the Product to add.
     * @return the new Product
     */
    public Product addProduct(Product Product) {
        return ProductDAO.add(Product);
    }

    /**
     * Retrieves all Products.
     *
     * @return a list of all Products.
     */
    public List<Product> getAllProducts() {
        return ProductDAO.getAllProducts();
    }

    /**
     * Retrieves a Product by its ID.
     *
     * @param ProductId the ID of the Product.
     * @return the Product with the specified ID, or null if not found.
     */
    public Product getProductById(int ProductId) {
        return ProductDAO.getProductById(ProductId);
    }

    /**
     * Updates an existing Product.
     *
     * @param Product the Product with updated details.
     */
    public void updateProduct(int ProductId, Product Product) {
        ProductDAO.update(ProductId, Product);
    }

    /**
     * Deletes a Product.
     *
     * @param ProductId The ID of the Product to delete.
     */
    public void deleteProduct(int ProductId) {
        ProductDAO.delete(ProductId);
    }
}