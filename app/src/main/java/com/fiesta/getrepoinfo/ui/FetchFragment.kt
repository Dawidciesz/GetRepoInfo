package com.fiesta.getrepoinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager

import com.fiesta.getrepoinfo.R
import com.fiesta.getrepoinfo.adapters.CommitListAdapter
import com.fiesta.getrepoinfo.databinding.FetchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchFragment : Fragment() {
    private val viewModel: FetchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fetch_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FetchFragmentBinding.bind(view)
        val commitListAdapter = CommitListAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = commitListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            viewModel.repo.observe(viewLifecycleOwner) { repo ->
                repoId.text = repo.id.toString()
            }
            viewModel.commits.observe(viewLifecycleOwner) { commits ->
                commitListAdapter.submitList(commits)
            }
            get.setOnClickListener(View.OnClickListener {
            viewModel.onGetButtonClick(setName.text.toString())
            })
        }
    }


}