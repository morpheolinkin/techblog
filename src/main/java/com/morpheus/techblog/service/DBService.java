package com.morpheus.techblog.service;

import com.morpheus.techblog.model.Post;
import com.morpheus.techblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Arrays.asList;

@Service
public class DBService {
    @Autowired
    private PostRepository postRepository;

    public void instantiateTestDatabase() {
        var post1 = new Post();
        post1.setAutor("Jefferson M Silva");
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto("O Docker é uma plataforma aberta para desenvolver, " +
                "enviar e executar aplicativos. O Docker permite que você separe seus aplicativos de sua " +
                "infraestrutura para que você possa fornecer software rapidamente. " +
                "Com o Docker, você pode gerenciar sua infraestrutura da mesma forma que gerencia seus aplicativos. " +
                "Aproveitando as metodologias do Docker para envio, teste e implantação de código rapidamente, " +
                "você pode reduzir significativamente o atraso entre escrever código e executá-lo em produção.");

        var post2 = new Post();
        post2.setAutor("Jefferson M Silva");
        post2.setData(LocalDate.now());
        post2.setTitulo("Docker");
        post2.setTexto("O Spring Boot é uma ferramenta que visa " +
                "facilitar o processo de configuração e " +
                "publicação de aplicações que utilizem o ecossistema Spring.");
        postRepository.saveAll(asList(post1, post2));
    }
}
