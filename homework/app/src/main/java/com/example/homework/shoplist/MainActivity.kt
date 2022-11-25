package com.example.homework.shoplist


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.Shop
import com.example.homework.databinding.ActivityMainBinding
import com.example.homework.login.LoginActivity
import com.example.homework.shopDetail.ShopDetail_activity
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast


const val ShopID = "shop id"


class MainActivity : AppCompatActivity() {
    private val shopListViewModel by viewModels<ShopsListViewModel>{
        ShopsListViewModelFactory(this)
    }

    private lateinit var binding:ActivityMainBinding

    private var clickTime: Long = System.currentTimeMillis()

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
        val HomePage = binding.HomePage
        val Indent = binding.indent
        val My = binding.My
        val ShowHomePage = binding.showHomePage
        val ShowIndent = binding.showIndent
        val ShowMy = binding.showMy
        //切换首页
        HomePage.setOnClickListener {
            ShowHomePage.visibility = View.VISIBLE
            ShowMy.visibility = View.GONE
            ShowIndent.visibility = View.GONE
        }
        //切换订单
        Indent.setOnClickListener{
            if(check_status()){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                ShowHomePage.visibility = View.GONE
                ShowMy.visibility = View.GONE
                ShowIndent.visibility = View.VISIBLE
            }

        }
        //切换我的
        My.setOnClickListener {
            if(check_status()){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                ShowHomePage.visibility = View.GONE
                ShowMy.visibility = View.VISIBLE
                ShowIndent.visibility = View.GONE
            }

        }


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis()-clickTime>2000){
                Toast.makeText(this,"再按一次退出!",Toast.LENGTH_SHORT).show()
                clickTime = System.currentTimeMillis()
            }
            else{
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
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