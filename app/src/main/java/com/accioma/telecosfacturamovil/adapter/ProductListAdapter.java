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
import com.accioma.telecosfacturamovil.activity.InvoiceFormActivity;
import com.accioma.telecosfacturamovil.activity.ProductFormActivity;
import com.accioma.telecosfacturamovil.model.Product;

import java.util.List;

/**
 * Created by marcelomora on 10/13/15.
 */
public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public final static String TAG = ProductListAdapter.class.getSimpleName();
    public final static int SELECT_INVOICE = 1;
    public final static int EDIT_PRODUCT = 2;

    private List<Product> mProducts;
    private Context mContext;
    private Activity mActivity;

    public ProductListAdapter(Context context, Activity activity){
        mContext = context;
        mActivity = activity;
    }

    public ProductListAdapter(Activity activity, List<Product> products){
        mActivity = activity;
        mProducts = products;
    }

    public List<Product> getProductList(){
        return mProducts;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder pvh = (ProductViewHolder)holder;
        pvh.descriptionText.setText(mProducts.get(position).getDescription());
        pvh.nameText.setText(mProducts.get(position).getName());
        pvh.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                int mode = mActivity.getIntent().getIntExtra(Consts.PARENT_ACTIVITY_MODE, -1);
                switch (mode) {
                    case SELECT_INVOICE:
                        Intent intent = new Intent();
                        intent.putExtra("PRODUCT", mProducts.get(position).getName());
                        ProductListAdapter.this.mActivity.setResult(InvoiceFormActivity.REQUEST_CUSTOMER,
                                intent);
                        break;
                    case EDIT_PRODUCT:
                        Intent intent1 = new Intent(ProductListAdapter.this.mActivity,
                                ProductFormActivity.class);
                        intent1.putExtra("PRODUCT", mProducts.get(position).getName());
                        Log.e(TAG, mActivity.getClass().getName());
                        view.getContext().startActivity(intent1);
                        break;
                }

                ProductListAdapter.this.mActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    private static class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private TextView nameText;
        private TextView descriptionText;
        private ItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            descriptionText = (TextView) itemView.findViewById(R.id.description);
            nameText = (TextView) itemView.findViewById(R.id.name);
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

    public void animateTo(List<Product> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Product> newModels) {
        for (int i = mProducts.size() - 1; i >= 0; i--) {
            final Product model = mProducts.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Product> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Product model = newModels.get(i);
            if (!mProducts.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Product> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Product model = newModels.get(toPosition);
            final int fromPosition = mProducts.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Product removeItem(int position) {
        final Product product = mProducts.remove(position);
        notifyItemRemoved(position);
        return product;
    }

    public void addItem(int position, Product model) {
        mProducts.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Product model = mProducts.remove(fromPosition);
        mProducts.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
