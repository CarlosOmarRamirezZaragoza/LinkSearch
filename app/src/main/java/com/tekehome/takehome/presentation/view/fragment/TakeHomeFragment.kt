package com.tekehome.takehome.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekehome.takehome.databinding.FragmentTakeHomeBinding
import com.tekehome.takehome.domain.entity.ServiceAliases
import com.tekehome.takehome.presentation.adapter.ServiceShortedUrlAdapter
import com.tekehome.takehome.presentation.model.Status
import com.tekehome.takehome.presentation.viewmodel.TakeHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TakeHomeFragment : Fragment() {
    private lateinit var _binding: FragmentTakeHomeBinding
    private val takeHomeViewModel: TakeHomeViewModel by viewModels()

    @Inject
    lateinit var serviceShortedUrlAdapter: ServiceShortedUrlAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTakeHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setUpRecyclers()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    takeHomeViewModel.statusFlow.collect { status ->
                        when (status) {
                            is Status.Success -> {
                                shortedSubmitList(status.items)
                            }

                            is Status.Failure -> {
                                onShowErrorToast(status.error)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onDecrementLingResourceActive() {
        if (!takeHomeViewModel.onLingResourceActive()) {
            takeHomeViewModel.onDecrementIdLingResource()
        }
    }

    private fun onShowErrorToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        onDecrementLingResourceActive()
    }

    private fun setUpRecyclers() {
        _binding.shortedUrlRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = serviceShortedUrlAdapter
        }
    }

    private fun shortedSubmitList(shortedList: List<ServiceAliases>) {
        serviceShortedUrlAdapter.submitList(shortedList) {
            onDecrementLingResourceActive()
        }
    }

    private fun setupUI() {
        _binding.apply {
            searchButton.setOnClickListener {
                takeHomeViewModel.onIncrementIdLingResource()
                takeHomeViewModel.onCreateAliases(searchEditText.text.toString())
            }
        }
    }
}
