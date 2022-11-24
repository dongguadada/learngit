package com.example.homework.login


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.homework.R
import com.example.homework.data.ToolUtils
import com.example.homework.databinding.ActivityLoginBinding
import com.example.homework.sign.SignActivity



class LoginActivity : AppCompatActivity() {
    var shpsd = true

    private lateinit var binding:ActivityLoginBinding

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
            val intent = Intent(this,SignActivity::class.java)
            startActivity(intent)
        }
        //显示密码
        SHpassword.setOnClickListener {
            if (shpsd){
                SHpassword.setImageResource(R.drawable.disshowpassword)
                LoginPasssword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                shpsd = false
            }
            else{
                SHpassword.setImageResource(R.drawable.showpassword)
                LoginPasssword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                shpsd = true
            }
        }
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