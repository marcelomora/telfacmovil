package com.accioma.telecosfacturamovil.db;

import com.accioma.telecosfacturamovil.model.InvoiceLine;
import com.accioma.telecosfacturamovil.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelomora on 10/17/15.
 */
public class InvoiceLineDAO {
    public static List<InvoiceLine> readInvoiceLines(long invoiceId){
        List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
        List<Product> products = new ArrayList<Product>();
        /*
        products.add(new Product(1, "AXE01", "Axe Deo 1", "11111", 4.10F));
        products.add(new Product(2, "AXE02", "Axe Deo 2", "22222", 4.10F));
        products.add(new Product(3, "AXE03", "Axe Deo 3", "33333", 4.10F));

        long[] taxes = {0L};
        invoiceLines.add(new InvoiceLine(1L, 1L, 1F, products.get(0), 4.3F, taxes, 4.3F));
        invoiceLines.add(new InvoiceLine(2L, 1L, 1F, products.get(0), 4.3F, taxes, 4.3F));
        invoiceLines.add(new InvoiceLine(3L, 1L, 1F, products.get(0), 4.3F, taxes, 4.3F));
        invoiceLines.add(new InvoiceLine(4L, 1L, 1F, products.get(0), 4.3F, taxes, 4.3F));
        */
        return invoiceLines;
    }
}
