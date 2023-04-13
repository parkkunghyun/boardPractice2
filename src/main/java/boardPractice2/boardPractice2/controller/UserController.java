package boardPractice2.boardPractice2.controller;

import boardPractice2.boardPractice2.entity.LogInInfo;
import boardPractice2.boardPractice2.entity.User;
import boardPractice2.boardPractice2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/logIn")
    public String logIn() {
        // 그냥 이동!
        return "user/logIn";
    }

    @PostMapping("/logIn")
    public String postLogIn(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession httpSession
            ) {
        // 이때 이제 service에서 로그인 하는 함수를 부르기
        // 일단 넘기기!
        try{
            User findUser = userService.getUser(email);
            if(findUser.getPassword().equals(password)) {
                LogInInfo logInInfo = new LogInInfo(findUser.getUserId(), findUser.getName(),findUser.getEmail());
                log.info("logInInfo = {}" , logInInfo);
                httpSession.setAttribute("logInInfo", logInInfo);
            }
            else {
                throw new RuntimeException("암호가 같지 않음!");
            }
        }catch (Exception e) {
            return "redirect:/logIn?error=true";
        }
        return "redirect:/board/list";
    }
    @GetMapping("/signIn")
    public String signIn() {
        // 그냥 이동!
        return "user/signIn";
    }
    @PostMapping("/signIn")
    public String postSignIn(
            @RequestParam("name")String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        User savedUser = userService.signIn(name,email,password);
        log.info("sign user = {}" , savedUser);
        return "redirect:/board/logIn";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("logInInfo");
        return "redirect:/board/list";
    }
}
