package com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyViewModel
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.databinding.ActivityRecyclerViewItemBinding


class MyAdapter(var myViewModel: MyViewModel) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    inner class MyHolder(itemView: ActivityRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var RVItem_TV_1 = itemView.RVItemTV1
        var RVItem_btn_1 = itemView.RVItemBtn1
        var RVItem_btn_2 = itemView.RVItemBtn2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ActivityRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return myViewModel.myDataList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        with(holder) {
            RVItem_TV_1.text = myViewModel.myDataList.value?.get(position).toString()
        }
    }
}