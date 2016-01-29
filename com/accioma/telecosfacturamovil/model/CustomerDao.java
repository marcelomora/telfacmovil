package com.accioma.telecosfacturamovil.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.accioma.telecosfacturamovil.model.Customer;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CUSTOMER.
*/
public class CustomerDao extends AbstractDao<Customer, Long> {

    public static final String TABLENAME = "CUSTOMER";

    /**
     * Properties of entity Customer.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Lastname = new Property(2, String.class, "lastname", false, "LASTNAME");
        public final static Property Firstname = new Property(3, String.class, "firstname", false, "FIRSTNAME");
        public final static Property Fin = new Property(4, String.class, "fin", false, "FIN");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Contact_name = new Property(6, String.class, "contact_name", false, "CONTACT_NAME");
        public final static Property Mobile_phone = new Property(7, String.class, "mobile_phone", false, "MOBILE_PHONE");
        public final static Property Phone = new Property(8, String.class, "phone", false, "PHONE");
        public final static Property Address1 = new Property(9, String.class, "address1", false, "ADDRESS1");
        public final static Property Address2 = new Property(10, String.class, "address2", false, "ADDRESS2");
        public final static Property Address3 = new Property(11, String.class, "address3", false, "ADDRESS3");
        public final static Property Location = new Property(12, String.class, "location", false, "LOCATION");
    };

    private DaoSession daoSession;


    public CustomerDao(DaoConfig config) {
        super(config);
    }
    
    public CustomerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CUSTOMER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'LASTNAME' TEXT," + // 2: lastname
                "'FIRSTNAME' TEXT," + // 3: firstname
                "'FIN' TEXT," + // 4: fin
                "'EMAIL' TEXT," + // 5: email
                "'CONTACT_NAME' TEXT," + // 6: contact_name
                "'MOBILE_PHONE' TEXT," + // 7: mobile_phone
                "'PHONE' TEXT," + // 8: phone
                "'ADDRESS1' TEXT," + // 9: address1
                "'ADDRESS2' TEXT," + // 10: address2
                "'ADDRESS3' TEXT," + // 11: address3
                "'LOCATION' TEXT);"); // 12: location
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CUSTOMER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Customer entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String lastname = entity.getLastname();
        if (lastname != null) {
            stmt.bindString(3, lastname);
        }
 
        String firstname = entity.getFirstname();
        if (firstname != null) {
            stmt.bindString(4, firstname);
        }
 
        String fin = entity.getFin();
        if (fin != null) {
            stmt.bindString(5, fin);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }
 
        String contact_name = entity.getContact_name();
        if (contact_name != null) {
            stmt.bindString(7, contact_name);
        }
 
        String mobile_phone = entity.getMobile_phone();
        if (mobile_phone != null) {
            stmt.bindString(8, mobile_phone);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(9, phone);
        }
 
        String address1 = entity.getAddress1();
        if (address1 != null) {
            stmt.bindString(10, address1);
        }
 
        String address2 = entity.getAddress2();
        if (address2 != null) {
            stmt.bindString(11, address2);
        }
 
        String address3 = entity.getAddress3();
        if (address3 != null) {
            stmt.bindString(12, address3);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(13, location);
        }
    }

    @Override
    protected void attachEntity(Customer entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Customer readEntity(Cursor cursor, int offset) {
        Customer entity = new Customer( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // firstname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fin
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // contact_name
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // mobile_phone
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // phone
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // address1
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // address2
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // address3
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // location
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Customer entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFirstname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFin(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContact_name(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMobile_phone(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPhone(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAddress1(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAddress2(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAddress3(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLocation(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Customer entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Customer entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
