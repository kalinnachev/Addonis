package com.telerikacademy.addonis.untilities;

import com.telerikacademy.addonis.models.Role;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserDto;
import com.telerikacademy.addonis.models.dto.UserUpdateDto;
import com.telerikacademy.addonis.repositories.contracts.RoleRepository;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperUser {
    private final RoleRepository roleRepository;
    private UserRepository userRepository;

    public ModelMapperUser(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User fromDto(UserDto userDto) {
        User user = new User();
        dtoObject(userDto, user);
        return user;
    }
    public User fromDto(UserUpdateDto userUpdateDto, int id) {
        User user = userRepository.getById(id);
        dtoUpdateObject(userUpdateDto,user);
        return user;
    }

    private void dtoObject(UserDto userDto, User user) {
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        Role role = roleRepository.getUserRole();
        user.setRole(role);
    }

    private void dtoUpdateObject(UserUpdateDto userUpdateDto, User user){
        user.setPassword(userUpdateDto.getPassword());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        user.setEmail(userUpdateDto.getEmail());
    }
}
