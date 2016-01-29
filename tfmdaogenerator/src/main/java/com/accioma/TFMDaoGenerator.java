package com.accioma;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class TFMDaoGenerator {
    public static void main(String args[]){
        Schema schema = new Schema(1, "com.accioma.telecosfacturamovil.model");

        Entity customer = schema.addEntity("Customer");

        customer.addIdProperty();
        customer.addStringProperty("name");
        customer.addStringProperty("lastname");
        customer.addStringProperty("firstname");
        customer.addStringProperty("fin");
        customer.addStringProperty("email");
        customer.addStringProperty("contact_name");
        customer.addStringProperty("mobile_phone");
        customer.addStringProperty("phone");
        customer.addStringProperty("address1");
        customer.addStringProperty("address2");
        customer.addStringProperty("address3");
        customer.addStringProperty("location");

        Entity product = schema.addEntity("Product");
        product.addIdProperty();
        product.addStringProperty("name");
        product.addStringProperty("description");
        product.addFloatProperty("standard_price");
        product.addFloatProperty("vat_tax");

        Entity authorization = schema.addEntity("Authorization");
        authorization.addIdProperty();
        authorization.addStringProperty("ruc_company");
        authorization.addStringProperty("estab");
        authorization.addStringProperty("ptoEmi");
        authorization.addIntProperty("nextSecuencial");
        authorization.addIntProperty("padding");
        authorization.addBooleanProperty("active");

        Entity invoice = schema.addEntity("Invoice");
        invoice.addIdProperty();
        invoice.addStringProperty("name");
        invoice.addDateProperty("issueDate");
        invoice.addFloatProperty("amountVat");
        invoice.addFloatProperty("amountDiscount");
        invoice.addFloatProperty("amountTotal");

        //ManyToOne customer - invoice
        Property customerId = invoice.addLongProperty("customerId").getProperty();
        invoice.addToOne(customer, customerId);

        //OneToMany invoice - customer
        ToMany customerToInvoice = customer.addToMany(invoice, customerId);
        customerToInvoice.setName("customerInvoices");

        Entity invoiceLine = schema.addEntity("InvoiceLine");
        invoiceLine.addIdProperty();
        invoiceLine.addFloatProperty("qtty");
        invoiceLine.addFloatProperty("amountVat");
        invoiceLine.addStringProperty("description");

        //ManyToOne InvoiceLine - invoice
        Property invoiceId = invoiceLine.addLongProperty("invoiceId").getProperty();
        invoiceLine.addToOne(invoice, invoiceId);

        //OneToMany Invoice - InvoiceLine
        ToMany invoiceToInvoiceLine = invoice.addToMany(invoice, invoiceId);
        invoiceToInvoiceLine.setName("invoiceToInvoiceLine");




        try {
            new DaoGenerator().generateAll(schema, ".");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
