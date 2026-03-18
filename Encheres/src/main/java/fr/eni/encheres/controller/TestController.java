package fr.eni.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {

   @GetMapping ("/formtest")
   public String showForm() {
      return "formTest-page";
   }

   @GetMapping("/info")
   public String showInfo() {
      return "infoTest-page";
   }

   @GetMapping("/")
   public String showAcceuil() {
      return "acceuilTest-page";
   }

   @GetMapping("/archive")
   public String showArchive() {
      return "archiveTest-page";
   }
}




