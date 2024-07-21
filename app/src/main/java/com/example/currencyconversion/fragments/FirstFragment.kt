package com.example.currencyconversion.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.currencyconversion.API.APIConstance.API_KEY
import com.example.currencyconversion.API.APIConstance.BASE_CODE
import com.example.currencyconversion.API.APIConstance.BASE_URL
import com.example.currencyconversion.API.APIConstance.REQUEST
import com.example.currencyconversion.API.APIService
import com.example.currencyconversion.MainActivity
import com.example.currencyconversion.MainViewModel
import com.example.currencyconversion.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.TemporalAmount

class FirstFragment : Fragment() {
    private lateinit var btConvert: Button
    private lateinit var editText: EditText
    private lateinit var spinnerCurrency: Spinner
    private lateinit var apiService: APIService
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()

        btConvert.setOnClickListener {
            val targetCode = spinnerCurrency.selectedItem.toString()
            val amount = editText.text.toString()
            if (isValidAmount(amount)) {
                getCurrencyExchangeRates(targetCode, amount)
            } else {
                Toast.makeText(requireContext(),
                    getString(R.string.toast_msg_invalid_amount), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidAmount(amount: String): Boolean {
        return try {
            val value = amount.toDouble()
            value >= 0
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun initRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }

    private fun getCurrencyExchangeRates(targetCode: String, amount: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val currencyData = apiService.getCurrency(
                API_KEY,
                REQUEST,
                BASE_CODE,
                targetCode,
                amount
            )

            requireActivity().runOnUiThread {
                currencyData.body()?.let { viewModel.setData(it) }
                (requireActivity() as MainActivity).goToSecondFragment()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        btConvert = view.findViewById(R.id.bt_convert)
        editText = view.findViewById(R.id.et_amount)
        spinnerCurrency = view.findViewById(R.id.spinner_currency)
        return view
    }
}