package com.dell.androidprojectpactice.ui.fastnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by dell on 2016/7/18.
 * Fast_Android_Network网络通信Demo
 */
@LayoutBinder(R.layout.ac_fast_android_network)
public class FastAndroidNetworkActivity extends AppCompatActivity {
    @Bind(R.id.constellation)
    EditText constellation;
    @Bind(R.id.type)
    Spinner type;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.all)
    TextView all;
    @Bind(R.id.color)
    TextView color;
    @Bind(R.id.health)
    TextView health;
    @Bind(R.id.love)
    TextView love;
    @Bind(R.id.money)
    TextView money;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.friend)
    TextView friend;
    @Bind(R.id.work)
    TextView work;
    @Bind(R.id.summary)
    TextView summary;
    @Bind(R.id.submit)
    Button submit;

    private ArrayAdapter adapter;
    private ArrayList<String> typeChoose = new ArrayList<>();
    private String queryTypeChoose;
    private String constellationName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
        initFastAndroid();
        initData();
        initView();
    }

    private void initData() {
        typeChoose.add("today");
        typeChoose.add("tomorrow");
        typeChoose.add("week");
        typeChoose.add("nextweek");
        typeChoose.add("month");
        typeChoose.add("year");
    }

    private void initView() {
        adapter = new ArrayAdapter<String>(FastAndroidNetworkActivity.this, R.layout.it_list, R.id.text, typeChoose);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                queryTypeChoose = typeChoose.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void request() {
        String key = "94609beb2164514cab63791cb9069081";
        constellationName = constellation.getText().toString();
        String url = "http://web.juhe.cn:8080/constellation/getAll?consName=" + constellationName + "&type=" + queryTypeChoose + "&key=" + key;
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Constellation constellation = getConstellation(response);
                        setValueToView(constellation);
                    }

                    @Override
                    public void onError(ANError ANError) {
                        Log.i("test", "Error Body:" + ANError.getErrorBody());
                    }
                });
    }

    private Constellation getConstellation(JSONObject response) {
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), Constellation.class);
    }

    private void setValueToView(Constellation constellation) {
        if (constellation != null) {
            name.setText("星座：" + constellation.getName());
            date.setText("日期：" + constellation.getDatetime());
            all.setText("综合指数：" + constellation.getAll());
            color.setText("幸运颜色：" + constellation.getColor());
            health.setText("健康指数：" + constellation.getHealth());
            love.setText("恋爱指数：" + constellation.getLove());
            money.setText("财运指数：" + constellation.getMoney());
            number.setText("幸运数字：" + constellation.getNumber());
            friend.setText("速配星座：" + constellation.getQFriend());
            summary.setText("总结：" + constellation.getSummary());
            work.setText("工作指数：" + constellation.getWork());
        }
    }

    private void initFastAndroid() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
    }

    @OnClick(R.id.submit)
    public void onClick() {
        request();
    }
}
