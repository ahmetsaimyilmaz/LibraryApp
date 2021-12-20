package com.example.libraryapp.customer;


import com.example.libraryapp.book.BookRepository;
import com.example.libraryapp.book.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    @Autowired
    BookRepository bookRepository;

    private final CustomerRepository CustomerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.CustomerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {
        return this.CustomerRepository.findAll();
    }

    public Optional<Customer> getSingleCustomer(int i) {
        return this.CustomerRepository.findById(Integer.valueOf(i));
    }

    public void delete(int Id) {
        this.CustomerRepository.deleteById(Integer.valueOf(Id));
    }

    public void resetBooks(int Id) {
        com.example.libraryapp.customer.Customer c1 = (Customer)this.CustomerRepository.getById(Integer.valueOf(Id));
        List<Book> books = this.bookRepository.findAll();
        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < c1.getRentedBooks().size(); j++) {
                if (((Book)books.get(i)).title.equals(((Book)c1.getRentedBooks().get(j)).title)) {
                    ((Book)books.get(i)).availability = "Available";
                    ((Book)books.get(i)).customer = null;
                }
            }
        }
    }

    public void saveMethod(Customer customer) {
        this.CustomerRepository.save(customer);
    }

    public void addNewCustomer(Customer customer) {
        System.out.println(customer.firstName);
        this.CustomerRepository.save(customer);
        List<Book> allBooks2 = this.bookRepository.findAll();
        for (int i = 0; i < allBooks2.size(); i++)
            System.out.println(((Book)allBooks2.get(i)).getId());
    }
}
