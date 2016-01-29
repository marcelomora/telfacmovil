package com.accioma.telecosfacturamovil.db;


import com.accioma.telecosfacturamovil.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelomora on 10/14/15.
 */
public class CustomerDAO {
    public static List<Customer> readAll(){
        List<Customer> customers = new ArrayList<Customer>();
        /*
        customers.add(new Customer(1, "1234567653001", "Gray", "Sacha"));
        customers.add(new Customer(2, "1767635624001", "Black", "Tori"));
        customers.add(new Customer(3, "0989777654001", "Malkova", "Mia"));
        customers.add(new Customer(4, "0738985789001", "Leone", "Sunni"));
        */
        return customers;
    }
}
