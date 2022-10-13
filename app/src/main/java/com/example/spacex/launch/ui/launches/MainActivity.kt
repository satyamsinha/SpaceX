package com.example.spacex.launch.ui.launches

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spacex.R
import com.example.spacex.launch.fragments.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var firstTime : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        firstTime =false
        showDialogFirstTime(context = this)

    }
    private fun showDialogFirstTime(context : Context) {

        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage(getString(R.string.welcome_message))
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id ->
                dialog.dismiss()
                addFragment(HomeFragment())

            })

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.app_name))
        alert.show()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        super.onKeyDown(keyCode, event)
        return if(supportFragmentManager.backStackEntryCount>1) {
            supportFragmentManager.popBackStack()
            false
        } else{
            finish()
            true
        }

    }
}

fun AppCompatActivity.addFragment(fragment: Fragment){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.add(R.id.container,fragment)
    transaction.addToBackStack(fragment::class.toString())
    transaction.commit()
}
