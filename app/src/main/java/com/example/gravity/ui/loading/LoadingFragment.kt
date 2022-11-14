package com.example.gravity.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.gravity.R
import com.example.gravity.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment : Fragment() {

    private lateinit var binding: FragmentLoadingBinding
    private val viewModel: LoadingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleState()
    }

    private fun navigateToWeb(link: String) {
        val direction = LoadingFragmentDirections.actionLoadingFragmentToWebFragment(link)
        NavHostFragment.findNavController(this@LoadingFragment).navigate(direction)
    }

    private fun handleState() {
        lifecycleScope.launch {
            viewModel.linksStateFlow.collect { state ->
                when (state) {
                    is LinksState.Loading -> binding.progressBar.isVisible = true
                    is LinksState.Error -> {
                        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_LONG).show()
                        binding.progressBar.isVisible = false
                    }
                    is LinksState.Success -> {
                        binding.progressBar.isVisible = false
                        navigateToWeb(state.link)
                    }
                    else -> binding.progressBar.isVisible = false
                }
            }
        }
    }
}