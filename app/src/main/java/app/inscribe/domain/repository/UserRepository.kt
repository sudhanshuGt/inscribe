package app.inscribe.domain.repository

import app.inscribe.domain.model.User
import app.inscribe.util.Resource


interface UserRepository {
    suspend fun getUser(): Resource<User>
    suspend fun saveUser(user: User): Resource<Unit>
}
