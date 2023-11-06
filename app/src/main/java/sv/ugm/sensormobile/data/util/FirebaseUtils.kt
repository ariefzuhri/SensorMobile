package sv.ugm.sensormobile.data.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

suspend fun <T : Any> DatabaseReference.addValueEventListenerFlow(
    dataType: Class<T>,
): Flow<RemoteResult<List<T>>> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val values = mutableListOf<T>()
                dataSnapshot.children.forEach { child ->
                    val sensorRecord = child.getValue(dataType)
                    sensorRecord?.let { values.add(it) }
                }
                trySend(RemoteResult.Success(values))
            }
            
            override fun onCancelled(error: DatabaseError) {
                error.toException().apply {
                    printStackTrace()
                    trySend(RemoteResult.Failure(this))
                }
                cancel()
            }
        }
        val subscription = addValueEventListener(listener)
        awaitClose { removeEventListener(subscription) }
    }
}