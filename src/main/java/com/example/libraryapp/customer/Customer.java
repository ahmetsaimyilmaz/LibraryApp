package com.example.libraryapp.customer;

import com.example.libraryapp.book.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    public String email;

    @OneToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, orphanRemoval = false)
    @JoinColumn(name = "customer_id")
    private List<Book> rentedBooks;

    public List<Book> getRentedBooks() {
        return this.rentedBooks;
    }

    public void setRentedBooks(List<Book> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public Customer() {}

    public void addBook(Book book) {
        if (this.rentedBooks == null)
            this.rentedBooks = new ArrayList<>();
        this.rentedBooks.add(book);
    }

    public Customer(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Customer{id=" + this.id + ", firstName='" + this.firstName + "', lastName='" + this.lastName + "', email='" + this.email + "'}";
    }
}
