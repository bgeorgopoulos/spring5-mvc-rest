package guru.springfamework.bootstrap;

import guru.springfamework.domain.Author;
import guru.springfamework.domain.Book;
import guru.springfamework.domain.Publisher;
import guru.springfamework.repositories.AuthorRepository;
import guru.springfamework.repositories.BookRepository;
import guru.springfamework.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, PublisherRepository publisherRepository1) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository1;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher randomPublisher = new Publisher("Basil Georgopoulos", "Eantos 73",
                "Athens", "Greece", "123456789");

        publisherRepository.save(randomPublisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(randomPublisher);

        randomPublisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(randomPublisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "2345");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(randomPublisher);

        randomPublisher.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(randomPublisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of Books: " + randomPublisher.getBooks().size());
    }
}
