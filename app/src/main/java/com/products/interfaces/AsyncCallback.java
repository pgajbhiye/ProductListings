package com.products.interfaces;

import com.products.models.ProductInfo;

/**
 * Created by Pallavi on 18-Apr-15.
 */
public interface AsyncCallback {
    public void onDone(ProductInfo[] productInfos);
}
