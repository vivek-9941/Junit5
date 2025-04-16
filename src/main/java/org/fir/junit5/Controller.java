package org.fir.junit5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class Controller {
    @Autowired
    bookrepository bookrepository;

    @GetMapping()
    public List<Book> findAll() {
        return bookrepository.findAll();
    }

    @GetMapping("{id}")
    public Book findbyid(@PathVariable int id) {
        return bookrepository.findById(id).orElse(null);
    }

    @PostMapping()
    public Book save(@RequestBody Book book) {
        return bookrepository.save(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) throws ClassNotFoundException {
        Optional<Book> bookOptional = bookrepository.findById(book.getId());
        if (bookOptional.isPresent()) {
            return bookrepository.save(book);
        } else {
            throw new ClassNotFoundException("not found");
        }
    }

    @DeleteMapping()
    public Book deleterecord(@RequestBody Book book) {
        if (bookrepository.existsById(book.getId())) {

           bookrepository.delete(book);
        } else {
            throw new RuntimeException("not found");
        }
        return book;
    }

}
