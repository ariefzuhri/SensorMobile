package sv.ugm.sensormobile.presentation.util

import android.content.Context
import android.widget.Toast

fun showToast(
    context: Context?,
    message: Any?,
) {
    message?.let {
        Toast.makeText(
            context?.applicationContext,
            it.toString(),
            Toast.LENGTH_LONG,
        ).show()
    }
}

fun Any?.asToast(context: Context?) {
    showToast(
        context = context,
        message = this,
    )
}