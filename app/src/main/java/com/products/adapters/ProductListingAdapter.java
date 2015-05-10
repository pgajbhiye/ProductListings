package com.products.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.products.R;
import com.products.models.ProductInfo;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Pallavi on 18-Apr-15.
 */
public class ProductListingAdapter extends BaseAdapter {


    private Context context;
    private ProductInfo[] products;
    private LayoutInflater inflater;


    public ProductListingAdapter(Context context, ProductInfo[] products){
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }


    public void sortByPrice(final boolean reverse){
        Arrays.sort(products, new Comparator<ProductInfo>() {
            @Override
            public int compare(ProductInfo lhs, ProductInfo rhs) {
                if(reverse){
                    return lhs.getPrice().compareTo(rhs.getPrice());
                }
                return rhs.getPrice().compareTo(lhs.getPrice());
            }
        });
        notifyDataSetChanged();
    }

    public void sortByRating(final boolean reverse){
        Arrays.sort(products, new Comparator<ProductInfo>() {
            @Override
            public int compare(ProductInfo lhs, ProductInfo rhs) {
                if(reverse)
                    return lhs.getRating().compareTo(rhs.getRating());
                return  rhs.getRating().compareTo(lhs.getRating());
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (products!=null)?products.length:0;
    }

    @Override
    public Object getItem(int position) {
        return products[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductWrapper holder;
        View rowview = convertView!=null?convertView:inflater.inflate(R.layout.product_row, parent, false);

        if(rowview.getTag() == null){
            holder = new ProductWrapper();

            holder.productImage = (ImageView) rowview.findViewById(R.id.productimage);
            holder.productName = (TextView) rowview.findViewById(R.id.productname);
            holder.productPrice = (TextView) rowview.findViewById(R.id.productprice);
            rowview.setTag(holder);
        }else{
            holder = (ProductWrapper) rowview.getTag();
        }

        ProductInfo productInfo = (ProductInfo)getItem(position);
        holder.productName.setText(productInfo.getName());
        holder.productPrice.setText(productInfo.getPrice());
        Picasso.with(context).load(productInfo.getImage()).into(holder.productImage);

        return rowview;
    }


    class ProductWrapper{
        ImageView productImage;
        TextView productName;
        TextView productPrice;

    }
}
