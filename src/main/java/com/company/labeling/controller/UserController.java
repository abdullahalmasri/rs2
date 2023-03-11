package com.company.labeling.controller;

import com.company.labeling.data.UserDto;
import com.company.labeling.security.JwtUtil;
import com.company.labeling.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api/v1/auth")
@Slf4j
public class UserController {
    private final UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/home")
    public String successPage() {

            return "welcome";

    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String getLogin(){
        return "login";
    }


    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    @ResponseBody
    public String getToken(
            @RequestBody UserDto userDto) throws Exception {

        log.info("the token will be generated for user {}", userDto.getName());
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userDto.getUsername(), userDto.getPassword())
                    );
        } catch (Exception e) {
            throw new Exception("invalid username or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response) {


        boolean isSecure = false;
        String contextPath = null;
        if (request != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            isSecure = request.isSecure();
            contextPath = request.getContextPath();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);
        if (response != null) {
            Cookie cookie = new Cookie("JSESSIONID", null);
            String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            cookie.setSecure(isSecure);
            response.addCookie(cookie);
        }
    }

}
