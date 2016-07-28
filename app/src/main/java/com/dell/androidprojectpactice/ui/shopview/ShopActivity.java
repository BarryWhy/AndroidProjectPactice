package com.dell.androidprojectpactice.ui.shopview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import me.wangyuwei.shoppoing.ShoppingView;

/**
 * Created by dell on 2016/7/14.
 */
@LayoutBinder(R.layout.ac_shopcart)
public class ShopActivity extends AppCompatActivity {

    private ShoppingView shoppingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        initShopView();
    }

    private void initShopView() {
        shoppingView = (ShoppingView) findViewById(R.id.shop);
        shoppingView.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                Toast.makeText(ShopActivity.this, "" + num, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMinusClick(int num) {
                Toast.makeText(ShopActivity.this, "" + num, Toast.LENGTH_SHORT).show();
            }
        });
        shoppingView.setTextNum(10);
    }
}
