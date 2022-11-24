package com.example.homework.shoplist


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.Shop
import com.example.homework.databinding.ActivityMainBinding
import com.example.homework.login.LoginActivity
import com.example.homework.shopDetail.ShopDetail_activity



const val ShopID = "shop id"
const val UserID = "user id"


class MainActivity : AppCompatActivity() {
    private val shopListViewModel by viewModels<ShopsListViewModel>{
        ShopsListViewModelFactory(this)
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        val Indent = binding.indent
        val My = binding.My
        val test = binding.test
        Indent.setOnClickListener{
            if(check_status()){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            print(test.height)
//            test.visibility= View.GONE
        }
        My.setOnClickListener {
            if(check_status()){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }

        }

    }

    private fun adapterOnClick(shop:Shop){
        val intent = Intent(this,ShopDetail_activity()::class.java)
        intent.putExtra(ShopID, shop.id)
        startActivity(intent)
    }

    private fun check_status():Boolean{
        val status = resources.getString(R.string.state)
        return status == "login in"

    }
}