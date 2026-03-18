package fr.eni.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"loggedUser"})
@Controller
@RequestMapping("/encheres")
public class TestController {

   @GetMapping ("/formtest")
   public String showForm() {
      return "formTest-page";
   }

   @GetMapping("/info")
   public String showInfo() {
      return "infoTest-page";
   }

   @GetMapping("/acceuil")
   public String showAcceuil() {
      return "acceuilTest-page";
   }

   @GetMapping("/archive")
   public String showArchive() {
      return "archiveTest-page";
   }
}




