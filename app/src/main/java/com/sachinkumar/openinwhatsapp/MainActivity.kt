package com.sachinkumar.openinwhatsapp

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var number = "0"
        if(intent.action == Intent.ACTION_PROCESS_TEXT)
        {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }

        if(number.isDigitsOnly())
        {
            startWhatsapp(number)
        }
        else
        {
            Toast.makeText(this,"Not A Valid Number",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(number: String) {
         val intent = Intent(Intent.ACTION_VIEW)
         intent.setPackage("com.whatsapp")

        val data : String = when {
            number[0]=='+' -> {
                number.substring(1)
            }
            number.length==10 -> {
                "91$number"
            }
            else -> {
                number
            }
        }

        intent.data = Uri.parse("https://wa.me/$data")
        startActivity(intent)
//        if(packageManager.resolveActivity(intent,0)!=null)
//        {
//            startActivity(intent)
//        }
//        else
//        {
//            Toast.makeText(this,"Whatsapp is not installed on your device",Toast.LENGTH_SHORT).show()
//        }

         finish()


    }
}

