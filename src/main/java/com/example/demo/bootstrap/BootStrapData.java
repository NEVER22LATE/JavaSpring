package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final  PublisherRepository publisherRepository;
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author bob = new Author("Bob","eric");
        Book javaFirst = new Book("Java first","1234");
        Publisher hindustanTimes = new Publisher("Hindustan Times", "New Delhi, India");
        publisherRepository.save(hindustanTimes);
        bob.getBooks().add(javaFirst);
        javaFirst.getAuthors().add(bob);
        javaFirst.setPublisher(hindustanTimes);
        hindustanTimes.getBooks().add(javaFirst);
        authorRepository.save(bob);
        bookRepository.save(javaFirst);
        publisherRepository.save(hindustanTimes);

        Author alice  = new Author("Alice","rom");
        Book pythonFirst = new Book("Python Firat", "2345");
        alice.getBooks().add(pythonFirst);
        pythonFirst.getAuthors().add(alice);
        pythonFirst.setPublisher(hindustanTimes);
        hindustanTimes.getBooks().add(pythonFirst);
        authorRepository.save(alice);
        bookRepository.save(pythonFirst);


        System.out.println("Author count "+ authorRepository.count());
        System.out.println("Book Count "+ bookRepository.count());
        System.out.println(publisherRepository.count());
    }
}
