package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.domain.model.User

interface UserRepository {

    suspend fun getUser(): User?

    suspend fun saveUser(user: User)
}
