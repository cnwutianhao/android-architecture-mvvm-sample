package com.tyhoo.mvvm.java.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tyhoo.mvvm.java.R;
import com.tyhoo.mvvm.java.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private FragmentMainBinding mViewDataBinding;

    private MainViewModel mMainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMainViewModel = MainActivity.obtainViewModel(getActivity());

        mViewDataBinding.setMain(mMainViewModel);
        mViewDataBinding.setLifecycleOwner(getActivity());

        mViewDataBinding.parseJsonBtn.setOnClickListener(view -> {
            Log.d(TAG, "Parse Json.");
            mMainViewModel.parseJson(requireContext());
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        mMainViewModel.start(requireContext());
    }
}
