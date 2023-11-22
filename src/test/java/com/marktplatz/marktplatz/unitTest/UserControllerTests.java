package com.marktplatz.marktplatz.unitTest;

import java.util.Arrays;
import java.util.List;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.controller.userController;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(userController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllUser_shouldReturnListOfUsers() throws Exception {

        List<User> userList = Arrays.asList(new User(), new User());
        when(userService.getAllUser()).thenReturn(userList);

        // Act and Assert
        mockMvc.perform((RequestBuilder) get("/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(userList.size()));
    }

    @Test
    public void getUserById_shouldReturnUser() throws Exception {

        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId));


        mockMvc.perform(get("/getUser/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    public void getUserByUsername_shouldReturnUser() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        when(userService.getByUsername(any()));


        mockMvc.perform(get("/getUserByUsername")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));
    }

    @Test
    public void addUser_shouldReturnAddedUser() throws Exception {

        UserDto userDto = new UserDto();
        when(userService.addUser(any()));


        mockMvc.perform(post("/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()));
    }

    @Test
    public void updateUser_shouldReturnNoContent() throws Exception {

        UserDto userDto = new UserDto();


        mockMvc.perform(put("/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService).updateUserById(userDto);
    }

    @Test
    public void deleteUser_shouldReturnNoContent() throws Exception {

        Long userId = 1L;


        mockMvc.perform(delete("/deleteUser/{id}", userId))
                .andExpect(status().isOk());

        verify(userService).deleteUser(userId);
    }

    @Test
    public void login_shouldReturnTrue() throws Exception {

        User user = new User();
        when(userService.login(any())).thenReturn(true);


        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }
}