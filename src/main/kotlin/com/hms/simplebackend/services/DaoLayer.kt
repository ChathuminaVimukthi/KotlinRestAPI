package com.hms.simplebackend.services

import com.hms.simplebackend.models.Author
import com.hms.simplebackend.models.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface AuthorDAO : MongoRepository<Author, String>
interface BookDAO : MongoRepository<Book, String> {
    fun findByAuthorId(id: String): List<Book>
    fun findByNameRegex(name: String): List<Book>
}