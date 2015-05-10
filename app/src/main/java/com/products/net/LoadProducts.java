package com.products.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.products.interfaces.AsyncCallback;
import com.products.models.ProductInfo;
import com.products.utils.LogUtils;
import com.products.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pallavi on 18-Apr-15.
 */
public class LoadProducts extends AsyncTask<String,String,ProductInfo[]> {

    private static final String LOG_TAG = "LoadProducts";
    private Context context;
    private AsyncCallback asyncCallback;

    public LoadProducts(Context context, AsyncCallback asyncCallback){
        this.context = context;
        this.asyncCallback = asyncCallback;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Utils.showProgressDialog(context);
    }

    @Override
    protected ProductInfo[] doInBackground(String... url) {
        //fetching data here
        HttpURLConnection connection = null;
        ProductInfo[] productInfos =null;
        URL serviceUrl = null;
        try {

            serviceUrl = new URL(url[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) serviceUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(LogUtils.Debug) Log.d(LOG_TAG, "responseCode for loading products " + responseCode);
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            productInfos = gson.fromJson(reader, ProductInfo[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }
        return productInfos;
    }

    @Override
    protected void onPostExecute(ProductInfo[] productInfos) {
        super.onPostExecute(productInfos);
        Utils.dismissProgressDialog();
        this.asyncCallback.onDone(productInfos);
    }

}
