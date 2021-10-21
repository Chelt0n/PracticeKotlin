package com.example.practicekotlin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import com.example.practicekotlin.R
import com.example.practicekotlin.databinding.ListOfCitiesFragmentBinding
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.viewmodel.AppState
import com.example.practicekotlin.viewmodel.MainViewModel

class ListOfCitiesFragment : Fragment(),OnClickWeather {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ListOfCitiesFragmentBinding
    private var idDataSetRu: Boolean = true
    private var adapter = ListOfCitiesFragmentAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListOfCitiesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.setOnClickWeather(this)



        binding.FAB.setOnClickListener {
            idDataSetRu = !idDataSetRu
            if (idDataSetRu) {
                viewModel.getRusCitiesFromLocal()
                binding.FAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getWorldCitiesFromLocal()
                binding.FAB.setImageResource(R.drawable.ic_earth)
            }
        }



        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            render(it)
        })
        viewModel.getDataFromLocalSource(true)

    }

    private fun render(appState: AppState) {
        when(appState){
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)


            }
            is AppState.Error -> TODO()
        }

    }


    companion object {
        fun newInstance() = ListOfCitiesFragment()
    }

    override fun onClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsWeatherFragment.WEATHER_KEY,weather)
        requireActivity().supportFragmentManager
            .beginTransaction().add(R.id.container, DetailsWeatherFragment.newInstance(bundle)).addToBackStack("")
            .commit()
    }


}