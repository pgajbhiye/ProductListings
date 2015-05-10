package com.products.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.products.R;
import com.products.models.ProductInfo;
import com.squareup.picasso.Picasso;

public class ProductDetailsFragment extends Fragment {

    private static final String LOG_TAG = "ProductDetailsFragment";
    private ProductInfo info;
    private String image;
    private String name;
    private String rating;
    private String price;
    private String users;
    private String type;
    private String lastUpdate;
    private String url;
    private String desc;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(false);
        Bundle bundle = getArguments();
        info  = bundle.getParcelable("details");
        image = info.getImage();
        name = info.getName();
        rating = info.getRating();
        price = info.getPrice();
        users = info.getUsers();
        type = info.getType();
        lastUpdate = info.getLast_update();
        url = info.getUrl();
        type = info.getType();
        desc = info.getDescription();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ImageView productImage = (ImageView)view.findViewById(R.id.image);
        Picasso.with(getActivity()).load(image).into(productImage);

        TextView productName = (TextView)view.findViewById(R.id.name);
        productName.setText(name);

        TextView description = (TextView)view.findViewById(R.id.description);
        description.setText(desc);

        TextView ratingVal = (TextView)view.findViewById(R.id.ratingvalue);
        ratingVal.setText(rating);

        TextView priceVal = (TextView)view.findViewById(R.id.pricevalue);
        priceVal.setText(price);

        TextView usersValue = (TextView)view.findViewById(R.id.usersvalue);
        usersValue.setText(users);

        TextView typeVal = (TextView)view.findViewById(R.id.typeval);
        typeVal.setText(type);

        TextView lastUpdateVal = (TextView)view.findViewById(R.id.lastupdateval);
        lastUpdateVal.setText(lastUpdate);
        return view;
    }
}
