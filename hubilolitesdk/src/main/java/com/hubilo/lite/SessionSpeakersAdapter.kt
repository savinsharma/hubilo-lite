package com.hubilo.lite

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.hubilo.lite.databinding.LayoutSessionSpeakerItemBinding

class SessionSpeakersAdapter(val context: Context, val speakersItemListDetail: List<com.hubilo.lite.apipack.SpeakersItemDetail?>, )
    : RecyclerView.Adapter<SessionSpeakersAdapter.SessionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): SessionsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return SessionsViewHolder(LayoutSessionSpeakerItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(
        holder: SessionSpeakersAdapter.SessionsViewHolder,
        position: Int
    ) {
        holder.setIsRecyclable(false)
        holder.bindSessionDetail(speakersItemListDetail[position])

        val layoutParams =
            holder.layoutSessionSpeakerItemBinding!!.relMain.layoutParams as RelativeLayout.LayoutParams
        if (itemCount == 1) {
            layoutParams.marginStart = (dp2px(context.resources, 12))
            layoutParams.marginEnd = (dp2px(context.resources, 12))
        } else {
            when (position) {
                0 -> {
                    layoutParams.marginStart = (dp2px(context.resources, 12))
                    layoutParams.marginEnd = (dp2px(context.resources, 6))
                }
                itemCount - 1 -> {
                    layoutParams.marginStart = (dp2px(context.resources, 6))
                    layoutParams.marginEnd = (dp2px(context.resources, 12))
                }
                else -> {
                    layoutParams.marginStart = (dp2px(context.resources, 6))
                    layoutParams.marginEnd = (dp2px(context.resources, 6))
                }
            }
        }

        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return speakersItemListDetail.size
    }

    inner class SessionsViewHolder(private val binding: LayoutSessionSpeakerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var layoutSessionSpeakerItemBinding: LayoutSessionSpeakerItemBinding? = null

        init {
            layoutSessionSpeakerItemBinding = binding
        }

        fun bind(speakersItem: SpeakersItem?) {
            val profileImage = "https://cdn.v2dev.demohubilo.com/" + "speaker/" +  + "/150" + speakersItem?.profileImg
            layoutSessionSpeakerItemBinding!!.tvSessionSpeakerName.text = speakersItem?.name
            layoutSessionSpeakerItemBinding!!.
        }

        fun bindSessionDetail(speakersItem: com.hubilo.lite.apipack.SpeakersItemDetail?) {

        }
    }

    fun dp2px(resource: Resources, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(), resource.getDisplayMetrics()
        ).toInt()
    }
}