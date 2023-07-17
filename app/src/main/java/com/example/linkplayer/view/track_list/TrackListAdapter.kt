package com.example.linkplayer.view.track_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkplayer.databinding.ItemLayoutBinding
import com.example.musicapp.model.Track

class TrackListAdapter :
    ListAdapter<TrackListAdapter.TrackItemViewModel, TrackListAdapter.TrackViewHolder>(DiffUtilCallback()) {

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
    }

    inner class TrackViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: TrackItemViewModel) {
            with(binding) {
                textViewUserName.text = viewModel.track.artist
                textViewUserEmail.text = viewModel.track.title
                Glide.with(imageViewAvatar.context)
                    .load(viewModel.track.bitmapUri)
                    .into(imageViewAvatar)
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

