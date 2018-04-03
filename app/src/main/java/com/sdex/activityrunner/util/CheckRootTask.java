package com.sdex.activityrunner.util;

import android.os.AsyncTask;

import com.stericson.RootTools.RootTools;

/**
 * Author: Yuriy Mysochenko
 * Date: 05-Dec-17
 */

public class CheckRootTask extends AsyncTask<Void, Void, Integer> {

  public static final int ROOT_IS_NOT_AVAILABLE = 10;
  public static final int ACCESS_IS_NOT_GIVEN = 20;

  private final Callback callback;

  public CheckRootTask(Callback callback) {
    this.callback = callback;
  }

  @Override
  protected Integer doInBackground(Void[] params) {
    boolean rootAvailable = RootTools.isRootAvailable();
    if (!rootAvailable) {
      return ROOT_IS_NOT_AVAILABLE;
    }
    boolean accessGiven = RootTools.isAccessGiven();
    if (!accessGiven) {
      return ACCESS_IS_NOT_GIVEN;
    }
    return 0;
  }

  @Override
  protected void onPostExecute(Integer result) {
    super.onPostExecute(result);
    this.callback.onStatusChanged(result);
  }

  public interface Callback {

    void onStatusChanged(int status);
  }
}
