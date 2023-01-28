package fr.imacaron.groupe19.urgan.backend.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object FirebaseAPIManager {

    var auth: FirebaseAuth = Firebase.auth

}