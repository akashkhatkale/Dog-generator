package com.svg.dog_generator.common.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}