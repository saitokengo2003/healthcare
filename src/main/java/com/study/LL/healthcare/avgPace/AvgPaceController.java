package com.study.LL.healthcare.avgPace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.LL.healthcare.user.LoginService;

import org.springframework.ui.Model;

@Controller
public class AvgPaceController {
    @Autowired
    private AvgPaceService avgPaceService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/avgPace")
    public String getavgPace() {
        if (!loginService.isLogin()) {
            return "login";
        }
        return "avgPace/input";
    }

    @PostMapping("/avgPace")
    public String postavgPace(
        Model model,
        @RequestParam(name = "distance") float distance,
        @RequestParam(name = "time") float time) {
        AvgPaceData data = avgPaceService.excute(distance, time);
        model.addAttribute("avgPace", data);
        return "avgPace/result";
    }
}
