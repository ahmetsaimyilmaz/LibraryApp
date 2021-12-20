package com.example.libraryapp.customer;

import com.example.libraryapp.book.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"customer"})
public class CustomerController {
    private final CustomerService CustomerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.CustomerService = customerService;
    }

    @GetMapping({"/listofcustomers"})
    public String getCustomer(Model model) {
        List<Customer> allCustomers = this.CustomerService.getCustomer();
        model.addAttribute("customers", allCustomers);
        return "customerList";
    }

    @RequestMapping({"/newCustomer"})
    public String addNewCustomer(Model model) {
       Customer newCustomer = new Customer();
        model.addAttribute("newCustomerObject", newCustomer);
        return "userForm";
    }

    @RequestMapping({"/save"})
    public String registerNewCustomer(@ModelAttribute("newCustomerObject") Customer customer, Model model) {
        this.CustomerService.addNewCustomer(customer);
        List<Customer> allCustomers = this.CustomerService.getCustomer();
        model.addAttribute("customers", allCustomers);
        return "customerList";
    }

    @GetMapping({"/customers/edit/{id}"})
    public String showEditForm(@PathVariable("id") int theId, Model model) {
        Customer c1 = this.CustomerService.getSingleCustomer(theId).get();
        model.addAttribute("newCustomerObject", c1);
        return "userForm";
    }

    @GetMapping({"/customers/delete/{id}"})
    public String deleteCustomer(@PathVariable("id") int theId, Model model) {
        this.CustomerService.resetBooks(theId);
        this.CustomerService.delete(theId);
        return "redirect:/customer/listofcustomers";
    }

    @GetMapping({"/rentedBooks/{id}"})
    public String listOfRentedBooks(@PathVariable("id") int theId, Model model) {
        List<Book> allBooks = ((Customer)this.CustomerService.getSingleCustomer(theId).get()).getRentedBooks();
        model.addAttribute("books", allBooks);
        return "listOfRentedBooks";
    }

    @GetMapping({"/rentedBooks/{id}/deleteBook/{bookId}"})
    public String deleteBookFromRentedList(@PathVariable("id") int theId, @PathVariable("bookId") int bookId, Model model) {
        Customer c1 = this.CustomerService.getSingleCustomer(theId).get();
        for (int i = 0; i < c1.getRentedBooks().size(); i++) {
            if (((Book)c1.getRentedBooks().get(i)).id == bookId) {
                ((Book)c1.getRentedBooks().get(i)).availability = "Available";
                c1.getRentedBooks().remove(i);
                break;
            }
        }
        this.CustomerService.saveMethod(c1);
        List<Book> allBooks = ((Customer)this.CustomerService.getSingleCustomer(theId).get()).getRentedBooks();
        model.addAttribute("books", allBooks);
        return "redirect:/customer/rentedBooks/" + theId + "/";
    }
}
