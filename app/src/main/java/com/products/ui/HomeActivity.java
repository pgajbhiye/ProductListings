package com.products.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.products.R;
import com.products.ui.ProductListingsFragment;
import com.products.utils.Config;


public class HomeActivity extends ActionBarActivity {

    private ProductListingsFragment productListingsFragment;

    private boolean sortorder = true; // true of ascending order , false for descending order

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        productListingsFragment = new ProductListingsFragment();
        fragmentTransaction.add(R.id.frame_container, productListingsFragment, "ProductListings");
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.sortprice:
                productListingsFragment.sortProducts(Config.SORTBY.PRICE, sortorder);
                sortorder = !sortorder;
                break;

            case R.id.sortrating:
                productListingsFragment.sortProducts(Config.SORTBY.RATING, sortorder);
                sortorder = !sortorder;
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();

        }else{
            super.onBackPressed();

        }
    }
}
