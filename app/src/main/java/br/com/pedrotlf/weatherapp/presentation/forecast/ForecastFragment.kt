package br.com.pedrotlf.weatherapp.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedrotlf.weatherapp.databinding.FragmentForecastBinding

class ForecastFragment: Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ForecastFragmentArgs>()

    private val adapter by lazy {
        ForecastWeatherListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forecastList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ForecastFragment.adapter
        }

        adapter.submitList(args.weatherList.toList())
    }
}