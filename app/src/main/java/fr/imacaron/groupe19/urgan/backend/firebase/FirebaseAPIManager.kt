package fr.imacaron.groupe19.urgan.backend.firebase

import android.content.Intent
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.tasks.await


object FirebaseAPIManager {

    var auth: FirebaseAuth = Firebase.auth

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance();

    fun signOut() {
        auth.signOut()
    }

    fun signinUser(pseudo: String, email: String, password: String) {

        val user = hashMapOf(
            "pseudo" to pseudo,
            "email" to email,
            "password" to password,
            "likes" to arrayListOf<Long>(),
            "wishs" to arrayListOf<Long>()
        )
        db.collection("Users")
            .document(email)
            .set(user)
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        var res = false
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                res = task.isSuccessful
            }
            .await()
        return res
    }


    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    suspend fun getWishListIds(): ArrayList<Long> {
        var app_ids: ArrayList<Long> = ArrayList()
        db.collection("Users")
            .document(getCurrentUser()?.email.toString())
            .get()
            .addOnCompleteListener(OnCompleteListener<DocumentSnapshot>() {
                if (it.isSuccessful) {
                    println(it.result.data)
                    if (it.result.data?.get("wishs") != null)
                        app_ids = it.result.data?.get("wishs") as ArrayList<Long>
                }
            })
            .await()
        println(app_ids)

        return app_ids
    }

    suspend fun getLikeListIds(id: Long): ArrayList<Long> {
        var app_ids: ArrayList<Long> = ArrayList()
        db.collection("Users")
            .document(getCurrentUser()?.email.toString())
            .collection("Likes")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot>() {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        app_ids.add(document.data["app_id"] as Long)
                    }
                } else {
                    println("Error getting documents.\n" + it.exception)
                }
            }).await()
        return app_ids
    }



}