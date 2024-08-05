package com.backend.ecommerce;

import com.backend.ecommerce.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Database {
    private List<User> userList;
}
