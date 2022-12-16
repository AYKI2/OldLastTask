package service.impl;

import model.Book;
import service.BookService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService {
    private List<Book> bookList = new ArrayList<>();

    @Override
    public List<Book> createBooks(List<Book> books) {
        bookList = books;
        return bookList;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookList;
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookList.stream().filter(x-> x.getGenre().name().equals(genre)).toList();
    }

    @Override
    public Book removeBookById(Long id) {
        Book book1 = new Book();
        for (Book book : bookList) {
            if (book.getId().equals(id)){
                book1 = book;
                bookList.removeIf(x->x.getId().equals(id));
                return book1;
            }
        }
        return book1;
    }

    @Override
    public List<Book> sortBooksByPriceInDescendingOrder() {
        return bookList.stream().sorted(Comparator.comparing(Book::getPrice).reversed()).toList();
    }

    @Override
    public List<Book> filterBooksByPublishedYear() {
        List<Book> book = bookList.stream().sorted(Comparator.comparing(Book::getPublishedYear)).toList();
        return book;
    }

    @Override
    public List<Book> getBookByInitialLetter() {
        System.out.println("Write letter: ");
        String letter = new Scanner(System.in).next();
        return bookList.stream().filter(x->x.getName().contains(letter)).findFirst().stream().toList();
    }

    @Override
    public Book maxPriceBook() {
        return null;
    }
}
