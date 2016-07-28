package com.dell.androidprojectpactice.util.annotation;

import android.app.Activity;

/**
 * Created by dell on 2016/7/7.
 * 布局绑定实现类 传入Activity实现绑定
 */
public final class AnnotationParser {

    private AnnotationParser() {
    }

    public static void injectActivity(Activity activity) {
        if (null == activity) {
            return;
                }
        Class<Activity> activityClass = (Class<Activity>) activity.getClass();
        if          (isLayoutBinder(activityClass)) {
            LayoutBinder layoutBinder = activityClass.getAnnotation(LayoutBinder.class);
                activity.setContentView(layoutBinder.value());
        }
//        View decorView = activity.getWindow().getDecorView();
    }

    public static boolean isLayoutBinder(Class<?> c) {
                    return c.isAnnotationPresent(LayoutBinder.class);
    }
}
