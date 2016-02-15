import com.tothenew.bootcamp.gorm1.User

class BootStrap {

    def init = { servletContext ->
        List<User> users = createUsers()
        listRecords()
        listRecordsByIds(users.id)
    }

    List<User> createUsers() {
        List<User> users = []
        (1..10).each {
            User user = new User(name: "user ${it}", email: "user+${it}@gmail.com", dob: new Date() - it, balance: 1000 * it)
            if (user.save()) {
                users.add(user)
                log.info "User ${user} saved successfully"
            } else {
                log.error "Error saving user : ${user.errors.allErrors}"
            }
        }
        users
    }

    void listRecords() {
        User.list([sort: 'dob', order: 'desc', max: 5, offset: 0]).each { User user ->
            println "---${user.name}--${user.dob}"
        }
    }

    void listRecordsByIds(List<Long> ids) {
        List<User> users = User.getAll(ids)
    }

    def destroy = {
    }
}
