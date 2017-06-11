package com.beyond.framework.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class Util {

  private Util() {
  }

  public static <T> T getT(Object object, Class<T> type) {
    return type.isInstance(object) ? (T) object : null;
  }

  public static <T extends Activity> T getTActivity(Context context) {
    Activity activity = getT(context, Activity.class);
    return activity == null ? null : (T) activity;
  }

  public static <T extends Serializable> T getSerializable(Bundle bundle, String name) {
    Object result = bundle == null ? null : bundle.getSerializable(name);
    return result == null ? null : (T) result;
  }

  public static boolean checkEquals(Object object1, Object object2) {
    if (object1 == null && object2 == null) {
      return true;
    } else if (object1 == null || object2 == null) {
      return false;
    }
    return object1.equals(object2);

  }

  public static synchronized <T extends Serializable> T clone(T obj) {
    if (obj == null) {
      return null;
    }
    T result = null;
    ObjectOutputStream obs = null;
    ObjectInputStream ois = null;
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      obs = new ObjectOutputStream(out);
      obs.writeObject(obj);

      ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
      ois = new ObjectInputStream(ios);
      result = (T) ois.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeSilently(obs);
      closeSilently(ois);

    }
    return result;
  }

  public static void closeSilently(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
        closeable = null;
      } catch (Exception ignored) {
      }
    }
  }
}
