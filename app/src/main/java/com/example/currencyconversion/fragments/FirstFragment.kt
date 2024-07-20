package com.example.currencyconversion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.currencyconversion.API.APIConstance.APP_ID
import com.example.currencyconversion.API.APIConstance.BASE_URL
import com.example.currencyconversion.API.APIService
import com.example.currencyconversion.MainActivity
import com.example.currencyconversion.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment() {
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var spinner: Spinner
    private lateinit var apiService: APIService
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()

        button.setOnClickListener {
            val simbols = editText.text.toString()
            getCurrencyExchangeRates(simbols)
            val activity = requireActivity() as MainActivity
            activity.goToSecondFragment()
        }
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }

    private fun getCurrencyExchangeRates(simbols: String) {
        CoroutineScope(Dispatchers.IO).launch {
            apiService.getCurrency(
                APP_ID,
                BASE_URL,
                simbols
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        button = view.findViewById(R.id.button)
        editText = view.findViewById(R.id.editText)
        spinner = view.findViewById(R.id.spinner)
        return view
    }
}