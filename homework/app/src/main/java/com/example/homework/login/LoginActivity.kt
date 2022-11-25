package com.example.homework.login


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R
import com.example.homework.m.ToolUtils
import com.example.homework.databinding.ActivityLoginBinding
import com.example.homework.m.RegistResultContract
import com.example.homework.sign.SignActivity


class LoginActivity : AppCompatActivity() {
    var shpsd = true

    private lateinit var binding:ActivityLoginBinding

    private val ActivityResultFromRegist = registerForActivityResult(RegistResultContract()){ result ->
        Toast.makeText(applicationContext,result.toString(),Toast.LENGTH_SHORT).show()
        if (result == 1){
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val LoginId = binding.loginAccount
        val LoginPasssword = binding.loginPassword
        val SHpassword = binding.hspass
        val RememberPassword = binding.rememberPassword
        val SignUp = binding.SignUp
        val LoginIn = binding.loginIn
        SignUp.setOnClickListener {
            ActivityResultFromRegist.launch(1)
        }
        //显示密码
        SHpassword.setOnClickListener {
            shpsd = if (shpsd){
                SHpassword.setImageResource(R.drawable.disshowpassword)
                LoginPasssword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                false
            } else{
                SHpassword.setImageResource(R.drawable.showpassword)
                LoginPasssword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                true
            }
        }
        //登入
        LoginIn.setOnClickListener {
            System.out.println(ToolUtils.getWindowHeigh(this))
            System.out.println(LoginPasssword.text)

            if (RememberPassword.isChecked){

                val edit = getSharedPreferences("Account", Context.MODE_PRIVATE).edit()
                edit.putString("account", LoginId.text.toString())
                edit.putString("password", LoginPasssword.text.toString())
                edit.apply()
                Toast.makeText(this, "写入完成", Toast.LENGTH_LONG).show()
            }
        }




    }
}