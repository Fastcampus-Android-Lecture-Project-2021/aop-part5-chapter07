package fastcampus.aop.part5.chapter07.data.api

import com.google.firebase.firestore.FirebaseFirestore
import fastcampus.aop.part5.chapter07.domain.model.User
import kotlinx.coroutines.tasks.await

class UserFirestoreApi(
    private val firestore: FirebaseFirestore
) : UserApi {

    override suspend fun saveUser(user: User): User =
        firestore.collection("users")
            .add(user)
            .await()
            .let { User(it.id) }
}
