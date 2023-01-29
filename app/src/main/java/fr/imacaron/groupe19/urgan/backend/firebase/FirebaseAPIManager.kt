package fr.imacaron.groupe19.urgan.backend.firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import fr.imacaron.groupe19.urgan.data.User
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import okhttp3.internal.waitMillis

object FirebaseAPIManager {

    var auth: FirebaseAuth = Firebase.auth

    private var db = FirebaseFirestore.getInstance()

    fun signOut() {
        auth.signOut()
    }

    fun signinUser(pseudo: String, email: String, password: String) {

        val user = User(
            pseudo,
            email,
            password,
            arrayListOf<Long>(),
            arrayListOf<Long>()
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

        var documentSnapshot = db.collection("Users")
            .document(getCurrentUser()?.email.toString())
            .get()
            .await()

        val user = documentSnapshot.toObject(User::class.java)
        println(user)
        if (user != null) {
            app_ids = user.wishList!!
        }

        return app_ids
    }

    suspend fun addAppInWishList(app_id: Long) {
        var documentSnapshot = db.collection("Users")
            .document(getCurrentUser()?.email.toString())
            .get()
            .await()

        val user = documentSnapshot.toObject(User::class.java)

        user?.wishList?.add(app_id)

    }

    suspend fun getLikeListIds(id: Long): ArrayList<Long> {
        var app_ids: ArrayList<Long> = ArrayList()

        var documentSnapshot = db.collection("Users")
            .document(getCurrentUser()?.email.toString())
            .get()
            .await()

        val user = documentSnapshot.toObject(User::class.java)
        println(user)
        if (user != null) {
            app_ids = user.likeList!!
        }

        return app_ids
    }



}