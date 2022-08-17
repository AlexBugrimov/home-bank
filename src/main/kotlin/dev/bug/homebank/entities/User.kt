package dev.bug.homebank.entities

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val firstName: String?,

    val lastName: String?,

    val email: String?,

    @Column(unique = true)
    val login: String,

    val password: String,

    @ManyToOne
    val role: Role
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (login != other.login) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', email='$email', login='$login', role=$role)"
    }

}