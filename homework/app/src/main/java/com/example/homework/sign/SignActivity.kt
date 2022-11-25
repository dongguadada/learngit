package com.example.homework.sign

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val SignAccount = binding.signupAccount
        val SignPassword = binding.signupPassword
        val ConfirmPassword = binding.confirmPassword
        val regist = binding.regist
        //注册并登入
        //判断密码账号是否为空或者含有空格，保证两次密码相同
        regist.setOnClickListener {
            val intent = Intent().apply{
                putExtra("result",1)
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}