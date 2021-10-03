package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Tell to Spring framework that this is a Spring Managed component,
 * and I'm implementing the Interface CommandLineRunner
 */
@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");
        // --- Publisher
        Publisher pub = new Publisher("Pub name", "Address", "City", "State", "Zip");
        publisherRepository.save(pub);
        System.out.println("Number of Publishers: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);

        /*
        * Going to save those into the H2 DB
        */
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pub);

        Author rod = new Author("Rod", "Johnson");
        Book noEGB = new Book("J2EE Development without EJB ", "2354062103");
        rod.getBooks().add(noEGB);
        noEGB.getAuthors().add(rod);
        noEGB.setPublisher(pub);
        pub.getBooks().add(noEGB);

        authorRepository.save(rod);
        bookRepository.save(noEGB);
        publisherRepository.save(pub);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number Of Books: " + pub.getBooks().size());


    }
}
