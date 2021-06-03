package fastcampus.aop.part5.chapter07.domain.model

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId
    val id: String? = null
)
