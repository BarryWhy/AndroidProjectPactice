package com.dell.androidprojectpactice.ui.emptyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.barryzhang.temptyview.TEmptyView;
import com.barryzhang.temptyview.TViewUtil;
import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;
import com.dell.androidprojectpactice.util.pulltorefresh.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/11.
 */
@LayoutBinder(R.layout.ac_emptyview)
public class EmptyViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<String> data = new ArrayList<>();
    private static final int MAX_DATA = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        init();
    }

    private void init() {
        initView();
        initData();
        initAdapter();
        initEmptyView();
    }

    private void initEmptyView() {
        TEmptyView.init(TViewUtil.EmptyViewBuilder.getInstance(EmptyViewActivity.this)
                .setShowText(true)
                .setEmptyText("NO DATA")
                .setShowButton(false)
                .setShowIcon(true));
        TViewUtil.EmptyViewBuilder.getInstance(EmptyViewActivity.this).bindView(recyclerView);
    }

    private void initAdapter() {
        if (data.size() != 0) {
            itemAdapter = new ItemAdapter(EmptyViewActivity.this, data);
            itemAdapter.setOnMyItemClickListener(new ItemAdapter.OnMyItemClickListener() {
                @Override
                public void onClick(View view, int pos) {
                    data.remove(pos);
                    itemAdapter.notifyDataSetChanged();
                }
            });
        }
        recyclerView.setAdapter(itemAdapter);
    }

    private void initData() {
        for (int i = 0; i < MAX_DATA; i++) {
            data.add("This is item " + i);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmptyViewActivity.this, LinearLayoutManager.VERTICAL, false));
    }
}
