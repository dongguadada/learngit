package com.example.homework.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.Shop
import com.example.homework.shopDetail.ShopDetail_activity


const val ShopID = "shop id"

class MainActivity : AppCompatActivity() {

    private val shopListViewModel by viewModels<ShopsListViewModel>{
        ShopsListViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shopHeaderAdapter = ShopHeaderAdapter()
        val shopsAdapter = ShopsAdapter { shop->adapterOnClick(shop) }
        val concatAdapter = ConcatAdapter(shopHeaderAdapter,shopsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        shopListViewModel.shopsLiveData.observe(this){
            it?.let{
                shopsAdapter.submitList(it as MutableList<Shop>)
            }
        }
    }
    private fun adapterOnClick(shop:Shop){
        val intent = Intent(this,ShopDetail_activity()::class.java)
        intent.putExtra(ShopID, shop.id)
        startActivity(intent)
    }
}