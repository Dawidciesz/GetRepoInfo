package com.fiesta.getrepoinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.fiesta.getrepoinfo.R
import com.fiesta.getrepoinfo.adapters.CommitListAdapter
import com.fiesta.getrepoinfo.data.Commit
import com.fiesta.getrepoinfo.data.Committer
import com.fiesta.getrepoinfo.data.Info
import com.fiesta.getrepoinfo.data.RepoData
import com.fiesta.getrepoinfo.databinding.DetailsFragmentBinding
import com.fiesta.getrepoinfo.databinding.FetchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: FetchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }
    private val args: DetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailsFragmentBinding.bind(view)
        val commitListAdapter = CommitListAdapter()

        viewModel.onGetButtonClick(args.url)

        binding.apply {
            recyclerView.apply {
                adapter = commitListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            viewModel.commits.observe(viewLifecycleOwner) { commits ->
                commitListAdapter.submitList(commits)
            }
            viewModel.repoData.observe(viewLifecycleOwner) { repo ->
                val list = mutableListOf<Info>()
                for (item in repo) {
                    list.add(Info(item.sha, item.message, Commit(item.message, Committer(item.author))))
                }
                commitListAdapter.submitList(list)
                }

        }
    }


}