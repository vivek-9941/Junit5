package org.fir.junit5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//importtatnt for using mocktio 

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private MockMvc mockMvc;

    //for converting json to string
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter =  objectMapper.writer();

    @Mock
    bookrepository repository;
//automatically inject mock or spy objects into the class you want to test.
    @InjectMocks
    private Controller controller;

    Book b1 =new Book(1,"oop", "bal", 2021, 2);
    Book b2  =new Book(2,"dsa","urmila", 2023 , 3);
    Book b3  =new Book(3,"cg","paswan", 1999 , 5);

    @BeforeEach
    void setup(){
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public  void getallrecord()throws Exception{
        List<Book> records = new ArrayList<>(Arrays.asList(b1,b2,b3));
        Mockito.when(repository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                //the above two for sending request
                //jsonpath allows you to verify the content insidr the json
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].title").value("oop"));
    }


    @Test
    void getbyid()throws Exception{
        Mockito.when(repository.findById(b1.getId())).thenReturn(Optional.ofNullable(b1));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.author").value("bal"));
    }

    @Test
    void savingTest() throws Exception {
        Book b4 = new Book(4,"oop", "bal", 2021, 2);
        Mockito.when(repository.save(Mockito.any(Book.class))).thenReturn(b4);
        String bookjson = objectWriter.writeValueAsString(b4);
        mockMvc.perform(MockMvcRequestBuilders.post("/book").contentType(MediaType.APPLICATION_JSON).content(bookjson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("bal"));

    }

  void deletesuccessTest()throws Exception{
        Mockito.when(repository.existsById(b1.getId())).thenReturn(true);
        Mockito.doNothing().when(repository).delete(b1);
      Book deletedBook = controller.deleterecord(b1);

      Assertions.assertEquals(b1, deletedBook); // assert that the same book is returned
      Mockito.verify(repository, Mockito.times(1)).delete(b1);
  }
    
}
