package fastcampus.aop.part5.chapter07.data.api

import fastcampus.aop.part5.chapter07.domain.model.User

interface UserApi {

    suspend fun saveUser(user: User): User
}
