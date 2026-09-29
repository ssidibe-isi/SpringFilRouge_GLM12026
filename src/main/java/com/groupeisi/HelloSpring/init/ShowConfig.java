package com.groupeisi.HelloSpring.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class ShowConfig implements CommandLineRunner {
    @Value("${groupeisi.gl.responsable.prenom}")
    private String responsablePrenom;

    @Value("${groupeisi.gl.responsable.nom}")
    private String responsableNom;

    @Value("${groupeisi.gl.responsable.email}")
    private String responsableEmail;

    @Value("${spring.datasource.password}")
    private String pwdDb;

    @Override
    public void run(String... args) throws Exception {
        log.info("Affichage des parametres de configuration");
        log.info("Prenom du responsable : {}", responsablePrenom);
        log.info("Nom du responsable : {}", responsableNom);
        log.info("Email du responsable : {}", responsableEmail);
        log.info("mot de passe : {}", pwdDb);
    }
}
