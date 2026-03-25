package fr.eni.encheres;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.services.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class EncheresApplication {

	private final ArticleService articleService;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public EncheresApplication(ArticleService articleService) {
        this.articleService = articleService;
    }

    public static void main(String[] args) {
		SpringApplication.run(EncheresApplication.class, args);
	}

	@Scheduled(fixedRate = 5000) // Vérifie toutes les minutes
	public void verifierStatutArticles() {
		List<Article> articles = articleService.selectAllArticles();
		for (Article article : articles) {
			articleService.validerStatutArticle(article);
		}
	}
}
