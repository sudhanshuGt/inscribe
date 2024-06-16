package app.inscribe.domain.usecase

import app.inscribe.domain.model.User
import app.inscribe.domain.repository.UserRepository
import app.inscribe.util.Resource
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Resource<Unit> {
        return userRepository.saveUser(user)
    }
}
