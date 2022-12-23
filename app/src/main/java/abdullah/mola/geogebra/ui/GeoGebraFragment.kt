package abdullah.mola.geogebra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abdullah.mola.geogebra.R
import abdullah.mola.geogebra.adapter.RcGeoGebraAdapter
import abdullah.mola.geogebra.databinding.FragmentGeoGebraBinding
import abdullah.mola.geogebra.viewModels.GeoGebraViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager

import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeoGebraFragment : Fragment() {
    @Inject
    lateinit var geoGebraAdapter: RcGeoGebraAdapter
    private lateinit var geoGebraViewModel: GeoGebraViewModel
    private var _binding: FragmentGeoGebraBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGeoGebraBinding.inflate(inflater, container, false)
        geoGebraViewModel = ViewModelProvider(this)[GeoGebraViewModel::class.java]
        setUpRecyclerView()
        context?.let { geoGebraViewModel.getDataFromServer(it) }
        geoGebraViewModel.getLocalGeoGebraItem().observe(viewLifecycleOwner) {
            geoGebraAdapter.swapData(it)

        }
        context?.getString(R.string.app_name)?.let { setActivityTitle(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        geoGebraAdapter.click {
            Navigation.findNavController(binding.root).navigate(
                GeoGebraFragmentDirections.actionGeoGebraFragmentToGeoGebraDetailsFragment(
                    Gson().toJson(it)
                )
            )

        }


    }

    private fun setUpRecyclerView() = binding.rvAllGeoGebra.apply {
        adapter = geoGebraAdapter
        layoutManager = GridLayoutManager(context, 2)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

}