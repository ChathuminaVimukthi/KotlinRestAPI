package com.hms.simplebackend.services

import com.hms.simplebackend.models.Book
import com.hms.simplebackend.util.BasicCrude
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service

class BookService(val bookDAO: BookDAO, private val authorDAO: AuthorDAO):BasicCrude<String,Book> {
    override fun getAll(pageable: Pageable): Page<Book> = bookDAO.findAll(pageable)

    override fun getById(id: String): Optional<Book> = bookDAO.findById(id)

    override fun insert(obj: Book): Book = bookDAO.insert(obj.apply { this.author = authorDAO.findById(obj.author.id!!).get() })

    @Throws(Exception::class)
    override fun update(obj: Book): Book{
        return if(bookDAO.existsById(obj.isbn!!)){//check if book exists because the save method will insert a record if does not exists
            bookDAO.save(obj.apply { this.author = authorDAO.findById(obj.author.id!!).get() })//re-insert author from db to avoid inconsistency
        }else{
            throw object: Exception("Book not found"){}
        }
    }

    override fun deleteById(id: String): Optional<Book> {
        return bookDAO.findById(id).apply {
            this.ifPresent { bookDAO.delete(it) }
        }
    }

}