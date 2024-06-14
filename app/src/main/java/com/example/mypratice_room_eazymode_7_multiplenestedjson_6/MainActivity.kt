package com.example.mypratice_room_eazymode_7_multiplenestedjson_6

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyAdapter.MyAdapter
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase.MyDatabase
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainBinding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel

    private lateinit var myMainJob: Job
    private lateinit var myObserJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainBinding = ActivityMainBinding.inflate(layoutInflater)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myMainJob = CoroutineScope(Dispatchers.Main).launch {
            joinAll(myViewModel.getMyVMInitJob())
            myRecyclerViewSet()
            myViewModel.myDataList.observe(this@MainActivity){
                myObserJob = CoroutineScope(Dispatchers.Main).launch {

                    bindingMainBinding.MainRV.adapter?.notifyDataSetChanged()
                }
            }
            setContentView(bindingMainBinding.root)
        }
    }

    fun onClick(view: View) {
        when (view) {
            bindingMainBinding.MainBtn1 -> {
                CoroutineScope(Dispatchers.IO).launch {
                    joinAll(myMainJob, MyDatabase?.insertInternetData()!!)
                    joinAll(myViewModel.updateMyDataList())
                    withContext(Dispatchers.Main){
                        bindingMainBinding.MainRV.adapter?.notifyDataSetChanged()
                    }
                }
            }

            bindingMainBinding.MainBtn2 -> {
                CoroutineScope(Dispatchers.IO).launch {
                    joinAll(myMainJob)
                    MyDatabase.getInstance()?.myDao()?.deleteAll()
                    joinAll(myViewModel.updateMyDataList())
                    withContext(Dispatchers.Main){
                        bindingMainBinding.MainRV.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    fun myRecyclerViewSet(){
        bindingMainBinding.MainRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = MyAdapter(myViewModel)
        }
    }
}