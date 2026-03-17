package fr.eni.encheres.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class EniFlashMessage {

    public int type; // 0 = success, 1 = error, 2 = warning
    public String message;

    public EniFlashMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public EniFlashMessage(String message) {
        this.message = message;
    }

    public String getClassName(){
        if (type == 0){
            return "uk-alert-success";
        }
        return "uk-alert-danger";
    }

    public static EniFlashMessage buildSuccess(String message) {
        return new EniFlashMessage(0, message);
    }

    public static EniFlashMessage buildError(String message) {
        return new EniFlashMessage(1, message);
    }

    public static void sendErrorFlash(RedirectAttributes redirectAttributes, String message){
        redirectAttributes.addFlashAttribute("flashMessage", EniFlashMessage.buildError(message));
    }

    public static void sendError(Model model, String message){
        model.addAttribute("flashMessage", EniFlashMessage.buildError(message));
    }

    public static void sendSuccessFlash(RedirectAttributes redirectAttributes, String message){
        redirectAttributes.addFlashAttribute("flashMessage", EniFlashMessage.buildSuccess(message));
    }

    public static void sendSuccess(Model model, String message){
        model.addAttribute("flashMessage", EniFlashMessage.buildSuccess(message));
    }

}
