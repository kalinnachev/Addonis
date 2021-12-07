package com.telerikacademy.addonis;

import com.telerikacademy.addonis.models.Role;
import com.telerikacademy.addonis.models.User;

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
}
