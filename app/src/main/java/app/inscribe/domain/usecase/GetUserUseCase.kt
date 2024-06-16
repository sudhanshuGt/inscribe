package app.inscribe.domain.usecase



import app.inscribe.domain.model.User
import app.inscribe.domain.repository.UserRepository
import app.inscribe.util.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Resource<User> {
        return userRepository.getUser()
    }
}
