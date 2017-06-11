package com.beyond.framework.util;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;

import java.util.List;

public final class ViewUtil {

  private ViewUtil() {
  }

  public static <V extends View> V findView(@NonNull
  View parentView, int id) {
    View view = parentView.findViewById(id);
    return view == null ? null : (V) view;
  }

  public static <V extends View> V findView(@NonNull
  Activity activity, int id) {
    View view = activity.findViewById(id);
    return view == null ? null : (V) view;
  }

  public static <V extends View> V findView(@NonNull
  Dialog dialog, int id) {
    View view = dialog.findViewById(id);
    return view == null ? null : (V) view;
  }

  public static <V> List<V> findTypeViews(@NonNull
  View view, @NonNull
  Class<V> type) {
    List<V> result = Lists.newArrayList();
    if (!(view instanceof ViewGroup)) {
      if (type.isInstance(view)) {
        result.add((V) view);
      }
      return result;
    }

    if (type.isInstance(view)) {
      result.add((V) view);
    }
    ViewGroup parent = (ViewGroup) view;
    for (int i = 0, count = parent.getChildCount(); i < count; i++) {
      result.addAll(findTypeViews(parent.getChildAt(i), type));
    }
    return result;
  }

  public static <V> List<V> findTypeViews(@NonNull
  Activity activity, @NonNull
  Class<V> type) {
    View view = activity.findViewById(android.R.id.content).getRootView();
    return findTypeViews(view, type);
  }
}
