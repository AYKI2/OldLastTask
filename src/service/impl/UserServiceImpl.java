package service.impl;

import enums.Gender;
import exception.BadRequestException;
import exception.UniqueConstrainException;
import model.Book;
import model.User;
import service.UserService;

import java.math.BigDecimal;
import java.util.*;

public class UserServiceImpl implements UserService {
    private List<User> userList = new ArrayList<>();

    @Override
    public String createUser(List<User> users) {
        userList = users;
        return "Successfully created!";
    }

    @Override
    public List<User> findAllUsers() {
        return userList;
    }

    @Override
    public User findUserById(Long id) {
        return (User) userList.stream().filter(x -> x.getId().equals(id));
    }

    @Override
    public String removeUserByName(String name) {
        boolean isRemoved = userList.removeIf(x->x.getName().equals(name));
        if (isRemoved) {
            return "Successfully removed!";
        }else {
            return "Removed failed!";
        }
    }

    @Override
    public void updateUser(Long id) {
        try {
            for (User user : userList) {
                if (!user.getId().equals(id)){
                    throw new UniqueConstrainException("No user with this ID!");
                }
                System.out.println("""
                        1 -> Id,
                        2 -> Name,
                        3 -> Surname,
                        4 -> Email,
                        5 -> Phone number,
                        6 -> Gender,
                        7 -> Money,
                        8 -> Books
                        """);
                int choice = new Scanner(System.in).nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.print("\nIDs that already exist: ");
                        for (User user1 : userList) {
                            System.out.print(user1.getId() + ", ");
                        }
                        System.out.println("Write new id: ");
                        Long newId = new Scanner(System.in).nextLong();
                        if (!user.getId().equals(id)) {
                            user.setId(newId);
                            System.out.println("Successfully changed!");
                        }else {
                            throw new UniqueConstrainException("This id already exists!");
                        }
                    }
                    case 2 -> {
                        System.out.println("Write new name: ");
                        String name = new Scanner(System.in).nextLine();
                        if (name.matches("[a-zA-Z ]*")) {
                            user.setName(name);
                            System.out.println("Successfully changed!");
                        }
                    }
                    case 3 -> {
                        System.out.println("Write new surname: ");
                        String surName = new Scanner(System.in).nextLine();
                        if (surName.matches("[a-zA-Z ]*")) {
                            user.setSurname(surName);
                            System.out.println("Successfully changed!");
                        }
                    }
                    case 4 -> {
                        System.out.println("Write new email: ");
                        String email = new Scanner(System.in).nextLine();
                        boolean isTrue = true;
                        if (email.contains("@")) {
                            for (User user1:userList) {
                                if (user1.getEmail().equals(email)) {
                                    isTrue = false;
                                    break;
                                }
                            }
                            if(isTrue){
                                user.setEmail(email);
                                System.out.println("Successfully changed!");
                            }else {
                                System.out.println("This email already exists!");
                            }
                        }else {
                            throw new BadRequestException("Email must contain @!");
                        }
                    }
                    case 5 -> {
                        System.out.println("Write new phone number: ");
                        String number = new Scanner(System.in).nextLine();
                        boolean isTrue = false;
                        for (User user1:userList) {
                            if (user1.getPhoneNumber().equals(number)){
                                isTrue = true;
                                break;
                            }
                        }
                        if (isTrue && number.length() >= 13 && number.contains("+996")) {
                            user.setPhoneNumber(number);
                            System.out.println("Successfully changed!");
                        }else {
                            throw new BadRequestException("error!");
                        }
                    }
                    case 6 -> {
                        System.out.println("""
                                Choice:
                                1 -> Male,
                                2 -> Female
                                """);
                        int choice1 = new Scanner(System.in).nextInt();
                        switch (choice1) {
                            case 1 -> user.setGender(Gender.MALE);
                            case 2 -> user.setGender(Gender.FEMALE);
                        }
                        System.out.println("Successfully changed!");
                    }
                    case 7 -> {
                        System.out.println("New money: ");
                        BigDecimal money = new Scanner(System.in).nextBigDecimal();
                        user.setMoney(money);
                        System.out.println("Successfully changed!");
                    }
                    case 8 -> {
                        System.out.println("");
                        List<Book> books = new ArrayList<>();
                    }
                }
            }
        }catch (UniqueConstrainException e ){
            e.getMessage();
        }catch (InputMismatchException e){
            System.out.println("Input error!");
        }catch (BadRequestException e){
            e.getMessage();
        }
    }

    @Override
    public void groupUsersByGender() {
        userList.stream().filter(x -> x.getGender().equals(Gender.MALE))
                .sorted(Comparator.comparing(User::getGender))
                .toList().forEach(System.out::println);
        userList.stream().filter(x -> x.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(User::getGender))
                .toList().forEach(System.out::println);
    }

    @Override
    public String buyBooks(String name, List<Book> books) {
        System.out.println("Your Id: ");
        Long id = new Scanner(System.in).nextLong();
        for (User user:userList) {
            if (user.getId().equals(id)) {
                books.forEach(System.out::println);
                System.out.println("Write book name: ");
                String bookName = new Scanner(System.in).nextLine();
                for (Book book : books) {
                    if (book.getName().equals(bookName) && user.getMoney().intValue() >= book.getPrice().intValue()) {
                        user.getBooks().add(book);
                        int money = user.getMoney().intValue() - book.getPrice().intValue();
                        user.setMoney(BigDecimal.valueOf(money));
                        books.removeIf(x -> x.getName().equals(bookName));
                        return "Successfully purchased!";
                    }
                }
            }
        }
        return "Purchased failed!";
    }
}
