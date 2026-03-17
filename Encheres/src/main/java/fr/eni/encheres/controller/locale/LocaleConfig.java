package fr.eni.encheres.controller.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig
{
    @Bean
    public LocaleResolver localeResolver() {
        // Locale forcée en francais
        return new FixedLocaleResolver(Locale.FRENCH);
    }
}