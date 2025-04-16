package org.fir.junit5;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bookrepository extends JpaRepository<Book, Integer> {

}
