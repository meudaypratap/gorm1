package com.tothenew.bootcamp.gorm1

class Book {
    String name

    static belongsTo = [user: User]

}
