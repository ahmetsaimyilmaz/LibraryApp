package com.example.libraryapp.book;

import com.example.libraryapp.customer.Customer;
import com.example.libraryapp.customer.CustomerRepository;
import com.example.libraryapp.customer.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"book"})
public class BookController {
    BookRepository BookRepository;

    CustomerRepository CustomerRepository;

    private final CustomerService CustomerService;

    private final BookService BookService;

    @Autowired
    public BookController(BookService bookService, CustomerService customerService, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.BookService = bookService;
        this.CustomerService = customerService;
        this.BookRepository = bookRepository;
        this.CustomerRepository = customerRepository;
    }

    @GetMapping({"/listofbooks"})
    public String getBook(Model model) {
        List<Book> allBooks = this.BookService.getBook();
        model.addAttribute("books", allBooks);
        return "bookList";
    }

    @GetMapping({"/rent/{bookid}"})
    public String rentBook(@PathVariable("bookid") int theId, Model model) {
        List<Customer> allCustomers = this.CustomerService.getCustomer();
        model.addAttribute("customers", allCustomers);
        return "rentBook";
    }

    @RequestMapping({"/rent/{bookid}/operation/{customerid}"})
    public String assignBook(@PathVariable("customerid") int theId, @PathVariable("bookid") int bookId, Model model) {
        Book b1 = this.BookService.getSingleBook(bookId).get();
        Customer c1 = this.CustomerService.getSingleCustomer(theId).get();
        b1.availability = "On loan: " + c1.firstName + " " + c1.lastName;
        c1.addBook(b1);
        this.CustomerRepository.save(c1);
        this.BookRepository.save(b1);
        List<Book> allBooks = this.BookService.getBook();
        model.addAttribute("books", allBooks);
        List<Book> sample = c1.getRentedBooks();
        for (int i = 0; i < sample.size(); i++)
            System.out.println(((Book)sample.get(i)).title + " " + ((Book)sample.get(i)).title);
        return "redirect:/book/listofbooks";
    }

    @RequestMapping({"/newBook"})
    public String addNewBook(Model model) {
        Book newBook = new Book();
        model.addAttribute("newBookObject", newBook);
        return "bookForm";
    }

    @RequestMapping({"/save"})
    public String registerBook(@ModelAttribute("newBookObject") Book book, Model model) {
        this.BookService.addNewBook(book);
        System.out.println("" + book.id + "xxxxxxxx");
        List<Book> allBooks = this.BookService.getBook();
        model.addAttribute("books", allBooks);
        return "bookList";
    }

    @GetMapping({"/books/edit/{id}"})
    public String showEditForm(@PathVariable("id") int theId, Model model) {
        Book b1 = this.BookService.getSingleBook(theId).get();
        model.addAttribute("newBookObject", b1);
        return "bookForm";
    }

    @GetMapping({"/books/delete/{id}"})
    public String deleteCustomer(@PathVariable("id") int theId, Model model) {
        this.BookService.delete(theId);
        return "redirect:/book/listofbooks";
    }
}
