package com.tothenew.bootcamp.gorm1

class Author {
    String name
    static hasMany = [bookAuthors: BookAuthor]
}
