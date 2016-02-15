package com.tothenew.bootcamp.gorm1

class UserInvitation {
    String code
    Boolean active

    static belongsTo = [user: User]
}
