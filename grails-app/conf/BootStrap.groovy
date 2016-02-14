import com.tothenew.bootcamp.gorm1.Author
import com.tothenew.bootcamp.gorm1.Book
import com.tothenew.bootcamp.gorm1.BookAuthor

class BootStrap {

    def init = { servletContext ->
    }

    def list(Integer offset, Integer max) {
        List bookList = Book.list(max: max, offset: offset)
        // List bookList = Book.list()
        println(bookList.id)
    }

    def getDemo() {
        println Book.get(1)
        println Book.getAll([1, 2, 3])
    }

    def validate() {
        Book book = new Book()
        book.validate()
        book.errors.allErrors.each {
            println it
            println "-----------------"
        }
    }

    def addTo() {
        Author author = Author.get(1)
        println "--------Before save ------"
        author.bookAuthors.each {
            println "${it}<br/><br/>"
        }
        author.addToBookAuthors(new BookAuthor(book: new Book(title: 'test'), author: author))
        author.save()
        println "<br/><br/>After save <br/><br/>"
        author..each {
            println "${it}<br/><br/>"
        }
    }

    def removeFrom() {
        Author author = Author.get(1)
        println "<br/><br/>Before save ${Book.count()}-- ${author.books.size()}<br/><br/>"
        author.bookAuthors.each {
            println "${it}<br/><br/>"
        }
        BookAuthor bookAuthor = author.bookAuthors[0]
        println "Removing Book ${bookAuthor}"
        if (book) {
            author.removeFromBookAuthors(bookAuthor)
            author.save(flush: true, failOnError: true)
        }
        println "<br/><br/>After save ${Book.count()}-- ${author.bookAuthors.size()}<br/><br/>"
        author.bookAuthors.each {
            println "${it}<br/><br/>"
        }
    }

    def findBy(String name) {
        println Author.findByName(name)
        println(Book.findAllByTitleInList(["Book-1", "Book-2"]))
    }

    def findOrSave() {
        println Book.findOrCreateByTitle("Grails")
        Book.findOrSaveByTitle("Groovy")
        println("Done")
    }

    def pagination(String name) {
        Book.findAllByTitleLike("${name}%").each {
            println "${it} <br/><br/>"
        }
        println "------------Paginated data---------------<br/> <br/>"
        Book.findAllByTitleLike("${name}%", [max: 5, offset: 2]).each {
            println "${it} <br/><br/>"

        }
    }

    def hql() {
        println Book.find("from Book where name=:name", [name: 'The Stand'])
    }
    def destroy = {
    }
}
