package ru.digitalleague.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.digitalleague.core.model.UserAccountEntity;
import ru.digitalleague.core.service.UserAccountService;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserAccountService userAccountService;

    @GetMapping("home")
    public String home(/*@RequestParam String login*/) {
//        return userAccountService.test(login);
        return "This is start page";
    }

    @GetMapping("auth")
    public String auth(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "auth " + principal.getName();
    }

    /**
     * этот метод работает только для ADMIN
     * @return List<UserAccountEntity>
     */
    @GetMapping("users")
    public ResponseEntity<List> showAllUsersLogin(){
        return new ResponseEntity<>(userAccountService.showAllUserLogin(), HttpStatus.OK);
    }
}
