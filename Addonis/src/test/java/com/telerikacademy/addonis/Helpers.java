package com.telerikacademy.addonis;

import com.telerikacademy.addonis.models.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Helpers {

    public static User createMockUser(){
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("mockUsername");
        mockUser.setPassword("mockPassword");
        mockUser.setFirstName("mockFirstName");
        mockUser.setLastName("mockLastName");
        mockUser.setEmail("mockEmail");
        mockUser.setPhoneNumber("mockPhoneNumber");
        mockUser.setPictureUrl("mockPictureUrl");
        mockUser.setBlocked(false);
        mockUser.setRole(createMockRole("Admin"));
        return mockUser;
    }

    public static Role createMockRole(String role) {
        var mockRole = new Role();
        mockRole.setId(1);
        mockRole.setRoleName(role);
        return mockRole;
    }

    private static Tag createMockTag() {
        var mockTag = new Tag();
        mockTag.setId(1);
        mockTag.setName("mockTag");
        return mockTag;
    }


}
