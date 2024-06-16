package app.inscribe.data.repository



import app.inscribe.domain.model.User
import app.inscribe.domain.repository.UserRepository
import app.inscribe.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getUser(): Resource<User> {
        val firebaseUser = auth.currentUser
        return if (firebaseUser != null) {
            val user = mapFirebaseUserToUser(firebaseUser)
            Resource.Success(user)
        } else {
            Resource.Error("User not logged in")
        }
    }

    override suspend fun saveUser(user: User): Resource<Unit> {
        return try {
            firestore.collection("users").document(user.id).set(user).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }

    private fun mapFirebaseUserToUser(firebaseUser: FirebaseUser): User {
        return User(
            id = firebaseUser.uid,
            name = firebaseUser.displayName ?: "",
            email = firebaseUser.email ?: "",
            profilePictureUrl = firebaseUser.photoUrl?.toString()
        )
    }
}
