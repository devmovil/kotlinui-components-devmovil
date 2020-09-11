package com.rnnzzo.uxdesign.util

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.rnnzzo.uxdesign.R

val animOptions = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}

fun Fragment.shareApp(){
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(Intent.EXTRA_TEXT, SHARE_APP_MESSAGE)
    intent.type = "text/plain"
    startActivity(Intent.createChooser(intent, "SHARE WITH"))
}


val APP_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.rnnzzo.uxdesign"
val SHARE_APP_MESSAGE = "Download Kotlin Ux Design at the Play Store!\n${APP_PLAY_STORE_URL}"
val SOURCE_CODE_URL = "https://github.com/devmovil/kotlinui-components-devmovil"