package com.accioma.telecosfacturamovil.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accioma.telecosfacturamovil.Consts;
import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.activity.CustomerFormActivity;
import com.accioma.telecosfacturamovil.activity.CustomerListActivity;
import com.accioma.telecosfacturamovil.activity.InvoiceFormActivity;
import com.accioma.telecosfacturamovil.model.Customer;

import java.util.List;

/**
 * Created by marcelomora on 10/13/15.
 */
public class CustomerListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public final static String TAG = CustomerListAdapter.class.getSimpleName();
    public final static int SELECT_INVOICE = 1;
    public final static int EDIT_CUSTOMER = 2;

    private List<Customer> mCustomers;
    private Context mContext;
    private Activity mActivity;

    public CustomerListAdapter(Context context, Activity activity){
        mContext = context;
        mActivity = activity;
    }

    public CustomerListAdapter(Activity activity, List<Customer> customers){
        mActivity = activity;
        mCustomers = customers;
    }

    public List<Customer> getCustomerList(){
        return mCustomers;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_card, parent, false);
        CustomerViewHolder cvh = new CustomerViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CustomerViewHolder cvh = (CustomerViewHolder)holder;
        cvh.fin.setText(mCustomers.get(position).getFin());
        cvh.name.setText(mCustomers.get(position).getLastname() + " " +
            mCustomers.get(position).getFirstname());

        cvh.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(mContext, "#" + position + "-"
                //        + customers.get(position).getFirstname() + " "
                //        + customers.get(position).getLastname(), Toast.LENGTH_SHORT).show();
                //intent.putExtra("CUSTOMER", customers.get(position).getFiscalId());
                int mode = mActivity.getIntent().getIntExtra(Consts.PARENT_ACTIVITY_MODE, -1);
                switch (mode) {
                    case SELECT_INVOICE:
                        Intent intent = new Intent();
                        intent.putExtra("CUSTOMER", mCustomers.get(position));
                        CustomerListAdapter.this.mActivity.setResult(InvoiceFormActivity.REQUEST_CUSTOMER,
                                intent);
                        break;
                    case EDIT_CUSTOMER:
                        Intent intent1 = new Intent(CustomerListAdapter.this.mActivity,
                                CustomerFormActivity.class);
                        intent1.putExtra("CUSTOMER", mCustomers.get(position));
                        Log.e(TAG, mActivity.getClass().getName());
                        view.getContext().startActivity(intent1);
                        break;
                }

                CustomerListAdapter.this.mActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    private static class CustomerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        public TextView fin;
        public TextView name;
        private ItemClickListener itemClickListener;

        public CustomerViewHolder(View itemView) {
            super(itemView);
            fin = (TextView) itemView.findViewById(R.id.fin);
            name = (TextView) itemView.findViewById(R.id.name);
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

    public void animateTo(List<Customer> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Customer> newModels) {
        for (int i = mCustomers.size() - 1; i >= 0; i--) {
            final Customer model = mCustomers.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Customer> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Customer model = newModels.get(i);
            if (!mCustomers.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Customer> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Customer model = newModels.get(toPosition);
            final int fromPosition = mCustomers.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Customer removeItem(int position) {
        final Customer customer = mCustomers.remove(position);
        notifyItemRemoved(position);
        return customer;
    }

    public void addItem(int position, Customer model) {
        mCustomers.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Customer model = mCustomers.remove(fromPosition);
        mCustomers.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
