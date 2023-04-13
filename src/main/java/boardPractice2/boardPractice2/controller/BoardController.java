package boardPractice2.boardPractice2.controller;

import boardPractice2.boardPractice2.entity.LogInInfo;
import boardPractice2.boardPractice2.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/list")
    public String list(HttpSession httpSession, Model model) {
        LogInInfo logInInfo = (LogInInfo) httpSession.getAttribute("logInInfo");
        model.addAttribute("logInInfo", logInInfo);

        // 이 곳은 데이터를 전부 받아서 1페이지씩 보여줘야함!
        // 일단 데이터를 다 받아서 보여주자!
        return "board/list";
    }

}
