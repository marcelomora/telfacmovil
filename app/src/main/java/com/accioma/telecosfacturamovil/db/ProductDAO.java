package com.accioma.telecosfacturamovil.db;


import com.accioma.telecosfacturamovil.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelomora on 10/14/15.
 */
public class ProductDAO {
    public static List<Product> readAll(){
        List<Product> products = new ArrayList<Product>();
        /*
        products.add(new Product(1, "AXE01", "Axe Deo 1", "11111", 4.10F));
        products.add(new Product(2, "AXE02", "Axe Deo 2", "22222", 4.10F));
        products.add(new Product(3, "AXE03", "Axe Deo 3", "33333", 4.10F));
        */
        return products;
    }
}
