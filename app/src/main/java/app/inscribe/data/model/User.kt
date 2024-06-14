package app.inscribe.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profilePictureUrl: String?
)