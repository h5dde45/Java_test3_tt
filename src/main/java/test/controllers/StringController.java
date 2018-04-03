package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.service.StringService;

@Controller
public class StringController {
    @Autowired
    private StringService stringService;

    @RequestMapping("main")
    public String main(Model model) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                stringService.depositThread1(1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                stringService.depositThread2(2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        model.addAttribute("str", stringService.getStr().toString());

        return "main";
    }

    @RequestMapping("clean")
    public String clean() {
        stringService.cleanerStr();
        return "redirect:/main";
    }

}
