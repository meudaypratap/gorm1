package com.tothenew.bootcamp.gorm1

class UserInvitation {
    String code = UUID.randomUUID()
    Boolean active = true

    static belongsTo = [user: User]
}
