package com.dell.androidprojectpactice.ui.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.toolbar.transparenttoolbar.ToolBarSroll;
import com.dell.androidprojectpactice.util.toolbar.transparenttoolbar.TransparentToolBar;

import java.util.ArrayList;
import java.util.List;

public class TransparentToolBarActivity extends AppCompatActivity implements TransparentToolBar.OnScrollStateListener{

    private List<String> list = new ArrayList<>();

//    private RecyclerView recyclerView;
//    private ItemAdapter adapter; //下拉刷新-动画
//    private FlyRefreshLayout flyRefresh; //下拉刷新-动画

    private TransparentToolBar mTransparentToolBar;
    private ToolBarSroll mMyScrollView;
    private TextView mHeadTv;
    private int mHeadValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        flyRefresh = (FlyRefreshLayout) findViewById(R.id.flyRefresh); //下拉刷新-动画
//        recyclerView = (RecyclerView) findViewById(R.id.list); //下拉刷新-动画
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); //下拉刷新-动画
//        initEvent(); //下拉刷新-动画

        initView();
        initToolBar();
        initListener();
    }

    //下拉刷新-动画
   /* private void initData() {
        for (int i = 0a; i < 3; i++) {
            list.add("This is" + i);
        }
        adapter.notifyDataSetChanged();
        flyRefresh.onRefreshFinish();
    }*/

    //下拉刷新-动画
    /*private void initEvent() {
        list.add("Number 0a");
        list.add("Number 1");
        list.add("Number 2");
        list.add("Number 3");
        list.add("Number 4");
        adapter = new ItemAdapter(TransparentToolBarActivity.this, list);
        adapter.setOnMyItemClickListener(new ItemAdapter.OnMyItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(TransparentToolBarActivity.this, "This is " + pos + " item", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        flyRefresh.setOnPullRefreshListener(new FlyRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh(FlyRefreshLayout view) {
                initData();
            }

            @Override
            public void onRefreshAnimationEnd(FlyRefreshLayout view) {

            }
        });
    }*/
    private void initView() {
        mTransparentToolBar = (TransparentToolBar) findViewById(R.id.toolbar);
        mMyScrollView = (ToolBarSroll) findViewById(R.id.bar_scrool);
        mHeadTv = (TextView) findViewById(R.id.text1);
    }


    private void initToolBar() {

        //必须设置ToolBar颜色值，也就是0~1透明度变化的颜色值
        mTransparentToolBar.setBgColor(getResources().getColor(android.R.color.holo_blue_light));

        mMyScrollView.setToolBar(mTransparentToolBar);
    }

    private void initListener() {
        mTransparentToolBar.setOnScrollStateListener(this);
        mHeadTv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeadValue = mHeadTv.getMeasuredHeight();

                //必须设置ToolBar最大偏移量，这会影响到0~1透明度变化的范围
                mTransparentToolBar.setOffset(mHeadValue);

                mHeadTv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void updateFraction(float fraction) {
        //ToolBar滚动回调的百分比0~1
    }
}
