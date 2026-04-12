package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.UserRequest;
import com.cinnamonshake.storefront.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/me")
    public String updateMe(
            @RequestBody UserRequest request,
            Authentication auth
    ) {
        return userService.updateUser(auth.getName(), request);
    }
}