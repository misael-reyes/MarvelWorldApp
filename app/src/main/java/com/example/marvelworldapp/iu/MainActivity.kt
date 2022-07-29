package com.example.marvelworldapp.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marvelworldapp.R
import com.example.marvelworldapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // preparamos la clase para hilt
class MainActivity : AppCompatActivity() {

    // preparamos el view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // conectamos la vista con binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_main)

        // instancia para el botton navigation
        val navView: BottomNavigationView = binding.bottomNavView

        // creamos el nav controller
        val navController = findNavController(R.id.navigation_host_fragment)

        /**
        // vamos a pasar los id's de los diferentes destinos
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersFragment, R.id.comicsFragment, R.id.seriesFragment
            )
        )

        // le damos acción a los botones de nuestro button navigation
        setupActionBarWithNavController(navController, appBarConfiguration)
        **/
        navView.setupWithNavController(navController)
    }
}