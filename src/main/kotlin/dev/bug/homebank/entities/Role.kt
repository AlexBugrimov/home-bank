package dev.bug.homebank.entities

import javax.persistence.*

@Entity
@Table
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(name = "code", unique = true, length = 32)
    private val name: String,

    @Column(length = 128)
    private val label: String
) {

    constructor(name: String, label: String) : this(null, name, label)
}