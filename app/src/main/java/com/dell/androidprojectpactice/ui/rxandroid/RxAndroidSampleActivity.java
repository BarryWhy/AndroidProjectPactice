package com.dell.androidprojectpactice.ui.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dell on 2016/7/7.
 */
@LayoutBinder(R.layout.ac_rxandroid)
public class RxAndroidSampleActivity extends AppCompatActivity {


    private static final String TAG = "RxAndroidSampleActivity";
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;
    private Observable<String> observable;
    private Subscriber<String> subscriber;

    Action1<String> onNextAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
//        createObservable();
//        createSubscriber();
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscriber.unsubscribe();
    }

    private void createRxAndroid() {
        Observable.just("Hello World!").subscribe(s -> {
            textView.setText(s);
            Toast.makeText(RxAndroidSampleActivity.this, s, Toast.LENGTH_SHORT).show();
        });
    }

    //创建观察者Subscriber
    private void createSubscriber() {
        /*subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxAndroidSampleActivity.this, "Bye, Subscriber", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxAndroidSampleActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(RxAndroidSampleActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };*/
        onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
                Toast.makeText(RxAndroidSampleActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
    }

    //创建被观察者Observable
    private void createObservable() {
        /*observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World!");
                textView.setText("Hello World!");
                subscriber.onCompleted();
            }
        });*/
        observable = Observable.just("Hello World!");
    }

    @OnClick(R.id.button)
    public void onClick() {
//        observable.subscribe(subscriber); //绑定观察者与被观察者
//        observable.subscribe(onNextAction);
        createRxAndroid();
    }


}
