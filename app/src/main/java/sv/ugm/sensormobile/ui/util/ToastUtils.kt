package sv.ugm.sensormobile.ui.util

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