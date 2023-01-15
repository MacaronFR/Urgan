package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}
