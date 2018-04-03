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

    private Thread thread1=new Thread(()->stringService.depositThread1(1));
    private Thread thread2=new Thread(()->stringService.depositThread2(2));

    @RequestMapping("main")
    public String main(Model model) {
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        model.addAttribute("count", stringService.getStr());

        return "main";
    }

}
