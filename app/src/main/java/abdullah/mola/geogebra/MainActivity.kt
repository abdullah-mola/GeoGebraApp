package abdullah.mola.geogebra

import abdullah.mola.geogebra.adapter.RcGeoGebraAdapter
import abdullah.mola.geogebra.databinding.ActivityMainBinding
import abdullah.mola.geogebra.viewModels.GeoGebraViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.app.AlertDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding get() = _binding
    private lateinit var geoGebraViewModel: GeoGebraViewModel
    private lateinit var navController: NavController

    @Inject
    lateinit var geoGebraAdapter: RcGeoGebraAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottom = supportActionBar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        geoGebraViewModel = ViewModelProvider(this)[GeoGebraViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.geoGebra) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.infoFragment,
                R.id.challenhensFragment,
                R.id.geoGebraDetailsFragment,
                R.id.fragment_splashscreen,
                R.id.geoGebraFragment
            )
        )

        binding.bottomNavigatinView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            // hide back button
            val appBarConfiguration =
                AppBarConfiguration.Builder(R.id.geoGebraFragment, R.id.fragment_splashscreen)
                    .build()
            setupActionBarWithNavController(navController, appBarConfiguration)

            // hide bottom navigation bar
            binding.bottomNavigatinView.visibility =
                if (destination.id == R.id.fragment_splashscreen|| destination.id == R.id.geoGebraDetailsFragment) {
                    View.GONE

                } else {
                    View.VISIBLE
                }

        }
        binding.bottomNavigatinView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.geoGebraFragment -> {
                    bottom?.title = "geoGebraFragment"
                    Navigation.findNavController(binding.geoGebra)
                        .navigate(R.id.geoGebraFragment)
                    true
                }
                R.id.challenhensFragment -> {
                    bottom?.title = "challenhensFragment"
                    Navigation.findNavController(binding.geoGebra)
                        .navigate(R.id.challenhensFragment)
                    true
                }
                R.id.infoFragment -> {
                    bottom?.title = "infoFragment"
                    Navigation.findNavController(binding.geoGebra)
                        .navigate(R.id.infoFragment)
                    true
                }
                else -> false
            }

        }
        geoGebraViewModel.getData(applicationContext)
        geoGebraViewModel.getLocalGeoGebraItem().observe(this) {
            geoGebraAdapter.swapData(it)

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.geoGebra)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
        override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.exit_dialog_title))
        builder.setMessage(getString(R.string.exit_dialog_message))
        builder.setPositiveButton(getString(R.string.option_yes)) { dialogInterface, _ ->
            dialogInterface.dismiss()
            super.onBackPressed()
        }
        builder.setNegativeButton(getString(R.string.option_no)) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

}



