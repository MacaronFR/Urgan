package fr.imacaron.groupe19.urgan.backend.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import fr.imacaron.groupe19.urgan.data.User
import kotlinx.coroutines.tasks.await

object FirebaseAPIManager {

    private var auth: FirebaseAuth = Firebase.auth

    private var db = FirebaseFirestore.getInstance()

    fun isLogged(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun signinUser(pseudo: String, email: String, password: String): Boolean {
        var isConnected = false;
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    isConnected = true

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


                } else {
                    isConnected = false
                }
            }
            .await()

        return isConnected;
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

    suspend fun setUser(user: User) {
        db.collection("Users")
            .document(getCurrentUser()?.email ?: "")
            .set(user)
            .await()
    }

    suspend fun getCurrentUser(): User? {
        return getUserByEmail(auth.currentUser?.email ?: "")
    }

    suspend fun getUserByEmail(email: String): User? {
        var documentSnapshot = db.collection("Users")
            .document(email)
            .get()
            .await()

        return documentSnapshot.toObject(User::class.java)
    }

}