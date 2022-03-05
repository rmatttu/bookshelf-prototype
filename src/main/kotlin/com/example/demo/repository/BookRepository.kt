package com.example.demo.repository

import com.example.demo.entity.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
}
