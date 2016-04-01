package com.accioma.telecosfacturamovil.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.activity.InvoiceFormActivity;
import com.accioma.telecosfacturamovil.activity.InvoiceLineActivity;
import com.accioma.telecosfacturamovil.db.InvoiceLineDAO;
import com.accioma.telecosfacturamovil.model.Invoice;
import com.accioma.telecosfacturamovil.model.InvoiceLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelomora on 10/17/15.
 */
public class InvoiceLineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final String TAG = InvoiceLineListAdapter.class.getSimpleName();

    private List<InvoiceLine> invoiceLines;
    private Activity mActivity;

    public InvoiceLineListAdapter() {
        this.invoiceLines = InvoiceLineDAO.readInvoiceLines(1L);
    }

    public InvoiceLineListAdapter(Activity activity){
        this.mActivity = activity;
        invoiceLines = new ArrayList<InvoiceLine>();
    }

    public static class InvoiceLineViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        public TextView productName;
        public TextView amountTotal;
        public TextView description;
        public TextView priceUnit;
        private ItemClickListener itemClickListener;

        public InvoiceLineViewHolder(View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.product_name);
            amountTotal = (TextView)itemView.findViewById(R.id.amount_total);
            description = (TextView)itemView.findViewById(R.id.product_description);
            priceUnit = (TextView)itemView.findViewById(R.id.price_unit);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_line_card, parent, false);
        InvoiceLineViewHolder ivh = new InvoiceLineViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InvoiceLineViewHolder ivh = (InvoiceLineViewHolder) holder;

        InvoiceLine il = invoiceLines.get(position);
        ivh.productName.setText(il.getIcc());
        ivh.priceUnit.setText(
                String.format("%.0f", il.getQtty()) + " X $" +
                String.format("%.2f", il.getPrice_unit()));
        String description = "[" + il.getName() + "] " + il.getDescription();
        ivh.description.setText(description);
        Float amountTotal = il.getQtty() * il.getPrice_unit();
        ivh.amountTotal.setText("$ " + String.format("%.2f", amountTotal) );

        ivh.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                /*
                //onClick test
                Log.i("Info", "Da el click");
                Snackbar.make(view, "No scan data received!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
                Intent intent = new Intent(InvoiceLineListAdapter.this.mActivity, InvoiceLineActivity.class);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(invoiceLines.size() == 0){
            invoiceLines = new ArrayList<InvoiceLine>();
            InvoiceLine invoiceLine = new InvoiceLine();
            invoiceLine.setIcc("");
            invoiceLine.setDescription("");
            invoiceLine.setPrice_unit(0F);
            invoiceLine.setQtty(0F);

            invoiceLines.add(invoiceLine);

        }
        Log.d(TAG, String.format("Lines %d", invoiceLines.size()));
        return invoiceLines.size();
    }
}
