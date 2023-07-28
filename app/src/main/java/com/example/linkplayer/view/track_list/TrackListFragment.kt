package com.example.linkplayer.view.track_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.linkplayer.databinding.FragmentTrackListBinding
import com.example.linkplayer.utils.observe
import com.example.linkplayer.model.Track
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController


class TrackListFragment : Fragment() {

    private lateinit var binding: FragmentTrackListBinding
    private val viewModel: TrackViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupRecyclerView() {
        val trackAdapter = TrackListAdapter()
        with(binding) {
            recyclerView.adapter = trackAdapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(root.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupObserver() {
        observe({
            viewModel.uiState.collect { state ->
                with(binding) {
                    progressBar.isVisible = state.screenState == ScreenState.LOADING
                    recyclerView.isVisible = state.screenState == ScreenState.IDLE
                    (recyclerView.adapter as TrackListAdapter).submitList(
                        state.tracks.map {
                            TrackListAdapter.TrackItemViewModel(it) {
                                navigateToTrack(it)
                            }
                        }
                    )
                }
            }
        })

        observe({
            viewModel.errorEvent.collect { state ->
                Toast.makeText(
                    activity,
                    viewModel.uiState.value.errorText,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun navigateToTrack(track: Track) {
        val action = TrackListFragmentDirections.actionFragmentTrackListToFragmentPlayer(track)
        findNavController().navigate(action)
//         findNavController().navigate(R.id.action_fragment_track_list_to_fragment_player)
    }
}