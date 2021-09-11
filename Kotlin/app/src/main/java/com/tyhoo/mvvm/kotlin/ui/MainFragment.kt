package com.tyhoo.mvvm.kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tyhoo.mvvm.kotlin.R
import com.tyhoo.mvvm.kotlin.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = (activity as MainActivity).obtainViewModel()
        viewDataBinding.main = mainViewModel
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewDataBinding.parseJsonBtn.setOnClickListener {
            mainViewModel.parseJson(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.start(requireContext())
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}