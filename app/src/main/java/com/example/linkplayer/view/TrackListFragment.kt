package com.example.linkplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkplayer.R
import com.example.linkplayer.databinding.FragmentTrackListBinding
import com.example.linkplayer.utils.Status
import com.example.linkplayer.view_model.TrackViewModel
import com.example.musicapp.model.Track
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.Observer


class TrackListFragment : Fragment(R.layout.fragment_track_list) {

    private lateinit var binding: FragmentTrackListBinding
    private val mainViewModel: TrackViewModel by viewModel()
    private lateinit var adapter: MainAdapter

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view = binding.root
//
////        setContentView(view)
//        setupUI()
//        setupObserver()
//
//        return inflater.inflate(R.layout.fragment_player, container, false)
//    }

    //Apply actions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackListBinding.inflate(layoutInflater)
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_track_list_to_fragment_player)

            val view = binding.root

//        setContentView(view)
            setupUI()
            setupObserver()
        }


    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.tracks.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { tracks -> renderList(tracks) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    //    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(tracks: List<Track>) {
        adapter.addData(tracks)
        adapter.notifyDataSetChanged()
    }

}