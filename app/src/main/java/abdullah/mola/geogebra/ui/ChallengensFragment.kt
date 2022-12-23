package abdullah.mola.geogebra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abdullah.mola.geogebra.R
import androidx.appcompat.app.AppCompatActivity



class ChallengensFragment : Fragment() {
    private var _binding: ChallengensFragment? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_challenhens, container, false)
        context?.getString(R.string.app_name)?.let { setActivityTitle(getString(R.string.challenges)) }

        return view
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }
}
