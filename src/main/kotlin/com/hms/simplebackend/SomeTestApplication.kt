package com.hms.simplebackend

import com.hms.simplebackend.models.Author
import com.hms.simplebackend.models.Book
import com.hms.simplebackend.services.AuthorDAO
import com.hms.simplebackend.services.BookDAO
import com.hms.simplebackend.util.toLocalDate
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SomeTestApplication(private val bookDAO: BookDAO, private val authorDAO: AuthorDAO): ApplicationRunner {
    /* This will run after springboot full load*/
    override fun run(args: ApplicationArguments?) {
        if(bookDAO.count()<1) this.createBooks()
    }


    private fun createBooks(){
        this.cleanCollections()
        val george = authorDAO.insert(Author(name = "George R. R. Martin",birthDate =  "20-09-1948".toLocalDate()))
        val tolkien = authorDAO.insert(Author(name = "J. R. R. Tolkien", birthDate = "03-01-1892".toLocalDate()))

        val books = listOf(
                Book(isbn = "9780553573428",name = "A Storm of Swords", publishedYear = 2011, author = george),
                Book(isbn = "9780553579901", name = "A clash of kings", publishedYear = 2005, author = george),
                Book(isbn = "9780618260553", name = "The Return of the King", publishedYear = 2002, author = tolkien)
        )
        bookDAO.insert(books)
    }

    private fun cleanCollections(){
        authorDAO.deleteAll()
        bookDAO.deleteAll()
    }
}

fun main(args: Array<String>) {
    runApplication<SomeTestApplication>(*args)
}
