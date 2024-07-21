package com.example.currencyconversion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.currencyconversion.MainActivity
import com.example.currencyconversion.MainViewModel
import com.example.currencyconversion.R

class SecondFragment : Fragment() {
    private lateinit var btBack: Button
    private lateinit var tvResult: TextView
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()?.observe(viewLifecycleOwner) {
            tvResult.text = it.conversion_result.toString() + " ${it.target_code}"
        }

        btBack.setOnClickListener {
            (requireActivity() as MainActivity).goToFirstFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        tvResult = view.findViewById(R.id.tv_result)
        btBack = view.findViewById(R.id.bt_back)
        return view
    }
}