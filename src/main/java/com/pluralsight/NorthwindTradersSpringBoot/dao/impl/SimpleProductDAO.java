package com.pluralsight.NorthwindTradersSpringBoot.dao.impl;

import com.pluralsight.NorthwindTradersSpringBoot.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDAO implements IProductDAO {
    private List<Product> Products;

    public SimpleProductDAO() {
        this.Products = new ArrayList<>();
        // Add some initial Products
        Products.add(new Product(1, "A",1,10.0));
        Products.add(new Product(2,"B",2,20.0));
        Products.add(new Product(3,"C",3,30.0));
    }

    @Override
    public Product add(Product Product) {
        int maxId = 0;
        for (Product t : Products) {
            if (t.getProductId() > maxId) {
                maxId = t.getProductId();
            }
        }
        Product.setProductId(maxId + 1); // Increment max ID by 1
        Products.add(Product);
        return Product;
    }


    @Override
    public List<Product> getAllProducts() {
        return Products;
    }

    @Override
    public Product getProductById(int ProductId) {
        for (Product Product : Products) {
            if (Product.getProductId() == ProductId) {
                return Product;
            }
        }
        return null;
    }

    @Override
    public void update(int ProductId, Product Product) {
        int index = getProductIndex(ProductId);
        if (index != -1) {
            Products.set(index, Product);
        }
    }

    @Override
    public void delete(int ProductId) {
        int index = getProductIndex(ProductId);
        if (index != -1) {
            Products.remove(index);
        }
    }

    private int getProductIndex(int ProductId) {
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getProductId() == ProductId) {
                return i;
            }
        }
        return -1;
    }
}