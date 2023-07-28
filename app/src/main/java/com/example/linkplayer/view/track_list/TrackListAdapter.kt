package com.example.linkplayer.view.track_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkplayer.R
import com.example.linkplayer.databinding.ItemLayoutBinding
import com.example.linkplayer.model.Track

class TrackListAdapter :
    ListAdapter<TrackListAdapter.TrackItemViewModel, TrackListAdapter.TrackViewHolder>(
        DiffUtilCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
//        holder.itemView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putInt("trackNumber", position)
//            Navigation.findNavController(holder.itemView)
//                .navigate(R.id.action_fragment_track_list_to_fragment_player, bundle)
//        }

    }

    inner class TrackViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: TrackItemViewModel) {
            with(binding) {
                textViewTrackPerformer.text = viewModel.track.artist
                textViewTrackName.text = viewModel.track.title
                Glide.with(imageViewCover.context)
                    .load(viewModel.track.bitmapUri)
                    .into(imageViewCover)
                root.setOnClickListener {
                    viewModel.onTrackClicked(viewModel.track)
                }
            }
        }
    }

    class TrackItemViewModel(val track: Track, val onTrackClicked: (Track) -> Unit)

    private class DiffUtilCallback : DiffUtil.ItemCallback<TrackItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: TrackItemViewModel,
            newItem: TrackItemViewModel
        ) =
            oldItem.track.artist == newItem.track.artist && oldItem.track.title == newItem.track.title

        override fun areContentsTheSame(
            oldItem: TrackItemViewModel,
            newItem: TrackItemViewModel
        ) = oldItem.track.trackUri == newItem.track.trackUri
    }


}

