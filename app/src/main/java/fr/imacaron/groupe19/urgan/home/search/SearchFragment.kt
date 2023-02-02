package fr.imacaron.groupe19.urgan.home.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentSearchBinding
import fr.imacaron.groupe19.urgan.error.NetworkException
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.*

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val adapter = GameAdapter(listOf(), this)

        binding.list.adapter = adapter
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.requestFocus()
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Do something
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                GlobalScope.launch {
                    withContext(Dispatchers.IO + h) {
                        val res = try {
                            SteamAPIManager.getAppsByName(s.toString())
                        }catch (e: NetworkException){
                            withContext(Dispatchers.Main){
                                Toast.makeText(context, resources.getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
                            }
                            return@withContext
                        }
                        val appIds = res.map {
                            it.appid?.toLong() ?: 0
                        }
                        withContext(Dispatchers.Main) {
                            val adapter = GameAdapter(appIds, this@SearchFragment)
                            binding.list.adapter = adapter
                            binding.nbRes.text = resources.getString(R.string.nb_result, appIds.size)
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do something
            }
        })
    }
}
