package dev.bug.homebank.entities

import javax.persistence.*

@Entity
@Table
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "code", unique = true, length = 32)
    val name: String,

    @Column(length = 128)
    val label: String
) {

    constructor(name: String, label: String) : this(null, name, label)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Role

        if (id != other.id) return false
        if (name != other.name) return false
        if (label != other.label) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + label.hashCode()
        return result
    }

    override fun toString(): String {
        return "Role(id=$id, name='$name', label='$label')"
    }
}