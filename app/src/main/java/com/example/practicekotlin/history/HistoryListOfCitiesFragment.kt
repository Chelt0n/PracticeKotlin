package com.example.practicekotlin.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practicekotlin.R
import com.example.practicekotlin.databinding.FragmentHistoryBinding
import com.example.practicekotlin.databinding.ListOfCitiesFragmentBinding
import com.example.practicekotlin.details.DetailsWeatherFragment
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.viewmodel.AppState
import com.example.practicekotlin.viewmodel.HistoryViewModel
import com.example.practicekotlin.viewmodel.MainViewModel

class HistoryListOfCitiesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[HistoryViewModel::class.java] }
    private lateinit var binding: FragmentHistoryBinding
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, {
            render(it)
        })
        viewModel.getAllHistory()

    }

    private fun render(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
                binding.historyFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.SuccessMain -> {
                binding.historyRecyclerView.adapter = adapter
                binding.historyFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)

            }
            is AppState.Error -> {
            }
        }

    }


    companion object {
        fun newInstance() = HistoryListOfCitiesFragment()
    }


}