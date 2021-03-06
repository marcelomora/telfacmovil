package com.accioma.telecosfacturamovil.model;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.accioma.telecosfacturamovil.model.Invoice;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table INVOICE.
*/
public class InvoiceDao extends AbstractDao<Invoice, Long> {

    public static final String TABLENAME = "INVOICE";

    /**
     * Properties of entity Invoice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property IssueDate = new Property(2, java.util.Date.class, "issueDate", false, "ISSUE_DATE");
        public final static Property AmountVat = new Property(3, Float.class, "amountVat", false, "AMOUNT_VAT");
        public final static Property AmountDiscount = new Property(4, Float.class, "amountDiscount", false, "AMOUNT_DISCOUNT");
        public final static Property AmountTotal = new Property(5, Float.class, "amountTotal", false, "AMOUNT_TOTAL");
        public final static Property CustomerId = new Property(6, Long.class, "customerId", false, "CUSTOMER_ID");
        public final static Property InvoiceId = new Property(7, Long.class, "invoiceId", false, "INVOICE_ID");
    };

    private DaoSession daoSession;

    private Query<Invoice> customer_CustomerInvoicesQuery;
    private Query<Invoice> invoice_InvoiceToInvoiceLineQuery;

    public InvoiceDao(DaoConfig config) {
        super(config);
    }
    
    public InvoiceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'INVOICE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'ISSUE_DATE' INTEGER," + // 2: issueDate
                "'AMOUNT_VAT' REAL," + // 3: amountVat
                "'AMOUNT_DISCOUNT' REAL," + // 4: amountDiscount
                "'AMOUNT_TOTAL' REAL," + // 5: amountTotal
                "'CUSTOMER_ID' INTEGER," + // 6: customerId
                "'INVOICE_ID' INTEGER);"); // 7: invoiceId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'INVOICE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Invoice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        java.util.Date issueDate = entity.getIssueDate();
        if (issueDate != null) {
            stmt.bindLong(3, issueDate.getTime());
        }
 
        Float amountVat = entity.getAmountVat();
        if (amountVat != null) {
            stmt.bindDouble(4, amountVat);
        }
 
        Float amountDiscount = entity.getAmountDiscount();
        if (amountDiscount != null) {
            stmt.bindDouble(5, amountDiscount);
        }
 
        Float amountTotal = entity.getAmountTotal();
        if (amountTotal != null) {
            stmt.bindDouble(6, amountTotal);
        }
 
        Long customerId = entity.getCustomerId();
        if (customerId != null) {
            stmt.bindLong(7, customerId);
        }
    }

    @Override
    protected void attachEntity(Invoice entity) {
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
    public Invoice readEntity(Cursor cursor, int offset) {
        Invoice entity = new Invoice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // issueDate
            cursor.isNull(offset + 3) ? null : cursor.getFloat(offset + 3), // amountVat
            cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4), // amountDiscount
            cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5), // amountTotal
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // customerId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Invoice entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIssueDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setAmountVat(cursor.isNull(offset + 3) ? null : cursor.getFloat(offset + 3));
        entity.setAmountDiscount(cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4));
        entity.setAmountTotal(cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5));
        entity.setCustomerId(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Invoice entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Invoice entity) {
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
    
    /** Internal query to resolve the "customerInvoices" to-many relationship of Customer. */
    public List<Invoice> _queryCustomer_CustomerInvoices(Long customerId) {
        synchronized (this) {
            if (customer_CustomerInvoicesQuery == null) {
                QueryBuilder<Invoice> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CustomerId.eq(null));
                customer_CustomerInvoicesQuery = queryBuilder.build();
            }
        }
        Query<Invoice> query = customer_CustomerInvoicesQuery.forCurrentThread();
        query.setParameter(0, customerId);
        return query.list();
    }

    /** Internal query to resolve the "invoiceToInvoiceLine" to-many relationship of Invoice. */
    public List<Invoice> _queryInvoice_InvoiceToInvoiceLine(Long invoiceId) {
        synchronized (this) {
            if (invoice_InvoiceToInvoiceLineQuery == null) {
                QueryBuilder<Invoice> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.InvoiceId.eq(null));
                invoice_InvoiceToInvoiceLineQuery = queryBuilder.build();
            }
        }
        Query<Invoice> query = invoice_InvoiceToInvoiceLineQuery.forCurrentThread();
        query.setParameter(0, invoiceId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCustomerDao().getAllColumns());
            builder.append(" FROM INVOICE T");
            builder.append(" LEFT JOIN CUSTOMER T0 ON T.'CUSTOMER_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Invoice loadCurrentDeep(Cursor cursor, boolean lock) {
        Invoice entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Customer customer = loadCurrentOther(daoSession.getCustomerDao(), cursor, offset);
        entity.setCustomer(customer);

        return entity;    
    }

    public Invoice loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Invoice> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Invoice> list = new ArrayList<Invoice>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Invoice> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Invoice> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
