package com.tothenew.bootcamp.gorm1

class Book {
    String title
    static hasMany = [bookAuthors: BookAuthor]
}