package com.fiesta.getrepoinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiesta.getrepoinfo.R
import com.fiesta.getrepoinfo.adapters.HistoryAdapter
import com.fiesta.getrepoinfo.data.RepoData
import com.fiesta.getrepoinfo.databinding.HistoryFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(),HistoryAdapter.OnItemClickListener {
    private val viewModel: HistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = HistoryFragmentBinding.bind(view)
        val historyListAdapter = HistoryAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = historyListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            viewModel.repoData.observe(viewLifecycleOwner) { repo ->
                val list = mutableListOf<RepoData>()
                list.addAll(repo)
                historyListAdapter.submitList(repo)
            }
        }
    }

    override fun onitemItemClick(url: String) {
        val action = HistoryFragmentDirections.actionHistoryFragmentToDetailsFragment(url)
        findNavController().navigate(action)
//        viewModel.onUrlClick(url)
    }

}
