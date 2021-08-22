package com.tyhoo.mvvm.java.ui;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.tyhoo.mvvm.java.R;
import com.tyhoo.mvvm.java.data.User;

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> mDataLoading = new MutableLiveData<>();

    private final MutableLiveData<String> mUserData = new MutableLiveData<>();

    private String jsonResponse;

    public void start(Context context) {
        networkRequest(context);
    }

    public void parseJson(Context context) {
        if (Strings.isNullOrEmpty(jsonResponse)) {
            return;
        }

        Gson gson = new Gson();
        User user = gson.fromJson(jsonResponse, User.class);
        String text = context.getString(R.string.parse_text, user.getLogin(), user.getHtmlUrl(),
                user.getName(), user.getCompany(), user.getLocation());
        mUserData.setValue(text);
    }

    private void networkRequest(Context context) {
        mDataLoading.setValue(true);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.github.com/users/cnwutianhao";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d(TAG, "networkRequest: success");
                    jsonResponse = response;
                    mDataLoading.setValue(false);
                    mUserData.setValue(response);
                },
                error -> {
                    Log.d(TAG, "networkRequest: error");
                    mDataLoading.setValue(false);
                    mUserData.setValue("");
                });

        queue.add(stringRequest);
    }

    public LiveData<Boolean> getDataLoading() {
        return mDataLoading;
    }

    public MutableLiveData<String> getUserData() {
        return mUserData;
    }
}
