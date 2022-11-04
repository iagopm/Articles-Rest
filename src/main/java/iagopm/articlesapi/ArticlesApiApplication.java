package iagopm.articlesapi;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class ArticlesApiApplication {
	private static Logger logger = LogManager.getLogger(ArticlesApiApplication.class);


	/**
	 * Proyecto Spring Boot en java 1.8 que tenga un endpoint de tipo restful, al
	 * que se le pase un json con un artículo y sus características y que se pueda
	 * hacer lo típico (crear, modificar, eliminar, obtener, etc). Puntos a tener en
	 * cuenta: Separación de paquetes Gestión de excepciones Uso de bd h2 en memoria
	 * Test del repositorio, servicio y controlador A partir de aquí se valorará:
	 * Caché para las peticiones Pool de conexiones Logs en la app Modo y tiempo de
	 * entrega
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ArticlesApiApplication.class, args);
	}

	@PostConstruct
	private void logo() {
		logger.info("\n   _____         .__                          __  .__       .__                 \n"
				+ "  /  _  \\ ______ |__|          _____ ________/  |_|__| ____ |  |   ____   ______\n"
				+ " /  /_\\  \\\\____ \\|  |  ______  \\__  \\\\_  __ \\   __\\  |/ ___\\|  | _/ __ \\ /  ___/\n"
				+ "/    |    \\  |_> >  | /_____/   / __ \\|  | \\/|  | |  \\  \\___|  |_\\  ___/ \\___ \\ \n"
				+ "\\____|__  /   __/|__|          (____  /__|   |__| |__|\\___  >____/\\___  >____  >\n"
				+ "        \\/|__|                      \\/                    \\/          \\/     \\/ ");
	}

}
