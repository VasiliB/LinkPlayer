package com.example.linkplayer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkplayer.databinding.ItemLayoutBinding
import com.example.musicapp.model.Track


class MainAdapter(
    private val tracks: ArrayList<Track>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {



    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.textViewUserName.text = track.artist
            binding.textViewUserEmail.text = track.title
            Glide.with(binding.imageViewAvatar.context)
                .load(track.bitmapUri)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
        DataViewHolder{
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)

    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(tracks[position])

    fun addData(list: List<Track>) {
        tracks.addAll(list)
    }
}