import com.tothenew.bootcamp.gorm1.User

class BootStrap {

    def init = { servletContext ->
        List<User> users = createUsers()
        users.each {
            findById(it.id)
        }
        users.each {
            getById(it.id)
        }
        findByProperty()
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

    void findById(Long id) {
        println "#######Finding User with Id :${id}##########"
        User user1 = User.findById(id)
        println "_______Finding User with Id :${id}___________"
        User user2 = User.findById(id)
        println "---------------------------------------------"
    }

    void getById(Long id) {
        println "#######Getting User with Id :${id}##########"
        User user1 = User.get(id)
        println "_______Getting User with Id :${id}___________"
        User user2 = User.get(id)
        println "---------------------------------------------"
    }

    void findByProperty() {
        User user = User.findByName("user 1")
        println "user---${user.id}---"
        User user1 = User.findByNameIlikeAndBalance("%user%", 5000)
        println "user1---${user1.id}---"
        User user2 = User.findByNameLikeOrBalance("%user%", 5000)
        println "user2---${user2.id}---"
        User user3 = User.findByNameIlikeOrBalance("%user%", 5000, [sort: 'balance', order: 'desc'])
        println "user3---${user3.id}---"
    }

    def destroy = {
    }
}
