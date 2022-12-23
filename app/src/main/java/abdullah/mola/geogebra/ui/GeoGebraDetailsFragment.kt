package abdullah.mola.geogebra.ui

import abdullah.mola.geogebra.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abdullah.mola.geogebra.data.local.Hit
import abdullah.mola.geogebra.databinding.FragmentGeoGebraDetailsBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson

class GeoGebraDetailsFragment : Fragment() {
    private val args: GeoGebraDetailsFragmentArgs by navArgs()
    private var _binding: FragmentGeoGebraDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGeoGebraDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val geoGebraName = args.title
        val geoGebr = Gson().fromJson(geoGebraName, Hit::class.java)
        binding.id.text = geoGebr.id
        binding.tvTitle.text = geoGebr.title
        binding.tvdataCreated.text = geoGebr.dateCreated
        binding.tvdatamodified.text = geoGebr.dateModified
        binding.tvAuthorName.text = geoGebr.creator.creator_name
        setActivityTitle(getString(R.string.details))
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }
}