package com.products.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.products.adapters.ProductListingAdapter;
import com.products.interfaces.AsyncCallback;
import com.products.models.ProductInfo;
import com.products.net.LoadProducts;
import com.products.R;
import com.products.utils.Config;

import java.util.Arrays;


/**
 * A fragment representing a list of Items.
 *Shows the each products items
 *
 */
public class ProductListingsFragment extends Fragment implements AbsListView.OnItemClickListener, AsyncCallback {

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;


    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ProductListingAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static ProductListingsFragment newInstance(String param1, String param2) {
        ProductListingsFragment fragment = new ProductListingsFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductListingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadProducts(getActivity(), this).execute(Config.FETCH_PRODUCTS_URL);
    }

    @Override
    public void onDone(ProductInfo[] productInfos) {
        mAdapter = new ProductListingAdapter(getActivity(), productInfos);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        /*bundle.putParcelable("details", info);
        detailsFragment.setArguments(bundle);*/

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter!=null)
            ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.productslist);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    Bundle bundle = new Bundle();
    ProductInfo info;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProductDetailsFragment detailsFragment = new ProductDetailsFragment();

        info = (ProductInfo)mAdapter.getItem(position);
        bundle.putParcelable("details", info);
        detailsFragment.setArguments(bundle);
       // getArguments().putAll(bundle);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, detailsFragment); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void sortProducts(Config.SORTBY sortby, boolean sortorder){
        switch (sortby){
            case PRICE:
                ((ProductListingAdapter)mAdapter).sortByPrice(sortorder);
                break;

            case RATING:
                ((ProductListingAdapter)mAdapter).sortByRating(sortorder);
                break;

        }
    }


}
