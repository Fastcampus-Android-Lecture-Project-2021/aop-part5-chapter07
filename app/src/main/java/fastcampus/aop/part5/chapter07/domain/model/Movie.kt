package fastcampus.aop.part5.chapter07.domain.model

import com.google.firebase.firestore.DocumentId
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @DocumentId
    val id: String? = null,

    @field:JvmField
    val isFeatured: Boolean? = null,

    val title: String? = null,
    val actors: String? = null,
    val country: String? = null,
    val director: String? = null,
    val genre: String? = null,
    val posterUrl: String? = null,
    val rating: String? = null,
    val averageScore: Float? = null,
    val numberOfScore: Int? = null,
    val releaseYear: Int? = null,
    val runtime: Int? = null
) : Parcelable
