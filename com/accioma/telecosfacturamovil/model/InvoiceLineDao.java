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

import com.accioma.telecosfacturamovil.model.InvoiceLine;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table INVOICE_LINE.
*/
public class InvoiceLineDao extends AbstractDao<InvoiceLine, Long> {

    public static final String TABLENAME = "INVOICE_LINE";

    /**
     * Properties of entity InvoiceLine.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Min = new Property(2, String.class, "min", false, "MIN");
        public final static Property Icc = new Property(3, String.class, "icc", false, "ICC");
        public final static Property Qtty = new Property(4, Float.class, "qtty", false, "QTTY");
        public final static Property Price_unit = new Property(5, Float.class, "price_unit", false, "PRICE_UNIT");
        public final static Property Price_total = new Property(6, Float.class, "price_total", false, "PRICE_TOTAL");
        public final static Property VatRate = new Property(7, Float.class, "vatRate", false, "VAT_RATE");
        public final static Property AmountVat = new Property(8, Float.class, "amountVat", false, "AMOUNT_VAT");
        public final static Property Description = new Property(9, String.class, "description", false, "DESCRIPTION");
        public final static Property InvoiceId = new Property(10, Long.class, "invoiceId", false, "INVOICE_ID");
    };

    private DaoSession daoSession;


    public InvoiceLineDao(DaoConfig config) {
        super(config);
    }
    
    public InvoiceLineDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'INVOICE_LINE' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'MIN' TEXT," + // 2: min
                "'ICC' TEXT," + // 3: icc
                "'QTTY' REAL," + // 4: qtty
                "'PRICE_UNIT' REAL," + // 5: price_unit
                "'PRICE_TOTAL' REAL," + // 6: price_total
                "'VAT_RATE' REAL," + // 7: vatRate
                "'AMOUNT_VAT' REAL," + // 8: amountVat
                "'DESCRIPTION' TEXT," + // 9: description
                "'INVOICE_ID' INTEGER);"); // 10: invoiceId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'INVOICE_LINE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, InvoiceLine entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String min = entity.getMin();
        if (min != null) {
            stmt.bindString(3, min);
        }
 
        String icc = entity.getIcc();
        if (icc != null) {
            stmt.bindString(4, icc);
        }
 
        Float qtty = entity.getQtty();
        if (qtty != null) {
            stmt.bindDouble(5, qtty);
        }
 
        Float price_unit = entity.getPrice_unit();
        if (price_unit != null) {
            stmt.bindDouble(6, price_unit);
        }
 
        Float price_total = entity.getPrice_total();
        if (price_total != null) {
            stmt.bindDouble(7, price_total);
        }
 
        Float vatRate = entity.getVatRate();
        if (vatRate != null) {
            stmt.bindDouble(8, vatRate);
        }
 
        Float amountVat = entity.getAmountVat();
        if (amountVat != null) {
            stmt.bindDouble(9, amountVat);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(10, description);
        }
 
        Long invoiceId = entity.getInvoiceId();
        if (invoiceId != null) {
            stmt.bindLong(11, invoiceId);
        }
    }

    @Override
    protected void attachEntity(InvoiceLine entity) {
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
    public InvoiceLine readEntity(Cursor cursor, int offset) {
        InvoiceLine entity = new InvoiceLine( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // min
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // icc
            cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4), // qtty
            cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5), // price_unit
            cursor.isNull(offset + 6) ? null : cursor.getFloat(offset + 6), // price_total
            cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7), // vatRate
            cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8), // amountVat
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // description
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10) // invoiceId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, InvoiceLine entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMin(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIcc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setQtty(cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4));
        entity.setPrice_unit(cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5));
        entity.setPrice_total(cursor.isNull(offset + 6) ? null : cursor.getFloat(offset + 6));
        entity.setVatRate(cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7));
        entity.setAmountVat(cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8));
        entity.setDescription(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setInvoiceId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(InvoiceLine entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(InvoiceLine entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getInvoiceDao().getAllColumns());
            builder.append(" FROM INVOICE_LINE T");
            builder.append(" LEFT JOIN INVOICE T0 ON T.'INVOICE_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected InvoiceLine loadCurrentDeep(Cursor cursor, boolean lock) {
        InvoiceLine entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Invoice invoice = loadCurrentOther(daoSession.getInvoiceDao(), cursor, offset);
        entity.setInvoice(invoice);

        return entity;    
    }

    public InvoiceLine loadDeep(Long key) {
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
    public List<InvoiceLine> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<InvoiceLine> list = new ArrayList<InvoiceLine>(count);
        
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
    
    protected List<InvoiceLine> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<InvoiceLine> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
