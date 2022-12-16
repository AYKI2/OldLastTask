import enums.Gender;
import enums.Genre;
import enums.Language;
import exception.UniqueConstrainException;
import model.Book;
import model.User;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static BookServiceImpl bookService = new BookServiceImpl();
    public static UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();
        List<Book> books4 = new ArrayList<>();
        User user1 = new User(1L,"Iskhak","Abdukhamitov","Iskhak@gmail.com","+996123123123", Gender.MALE,new BigDecimal(2000),books1);
        User user2 = new User(1L,"Iskhak","Abdukhamitov","Iskhak@gmail.com","+996123123123", Gender.MALE,new BigDecimal(2000),books2);
        User user3 = new User(1L,"Iskhak","Abdukhamitov","Iskhak@gmail.com","+996123123123", Gender.MALE,new BigDecimal(2000),books3);
        User user4 = new User(1L,"Iskhak","Abdukhamitov","Iskhak@gmail.com","+996123123123", Gender.MALE,new BigDecimal(2000),books4);
        List<User>users = new ArrayList<>(List.of(user1,user2,user3,user4));

        Book book1 = new Book(1L,"Harry Potter", Genre.FANTASY,new BigDecimal(500),"Iskhak", Language.ENGLISH, LocalDate.of(2002,2,22));
        Book book2 = new Book(1L,"Harry Potter", Genre.FANTASY,new BigDecimal(500),"Iskhak", Language.ENGLISH, LocalDate.of(2002,2,22));
        Book book3 = new Book(1L,"Harry Potter", Genre.FANTASY,new BigDecimal(500),"Iskhak", Language.ENGLISH, LocalDate.of(2002,2,22));
        Book book4 = new Book(1L,"Harry Potter", Genre.FANTASY,new BigDecimal(500),"Iskhak", Language.ENGLISH, LocalDate.of(2002,2,22));
        List<Book> books = new ArrayList<>(List.of(book1,book2,book3,book4));

        bookService.createBooks(books);
        userService.createUser(users);
    }
}