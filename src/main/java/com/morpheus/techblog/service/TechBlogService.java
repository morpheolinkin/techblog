package com.morpheus.techblog.service;

import com.morpheus.techblog.dto.PostDTO;
import com.morpheus.techblog.model.Post;
import com.morpheus.techblog.repository.PostRepository;
import com.morpheus.techblog.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechBlogService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Integer id) {
        Optional<Post> byId = postRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID" + id + ", tipo: " + Post.class.getName()));

    }

    public Post save(Post post) {
        post.setId(null);
        return postRepository.save(post);

    }

    public void delete(Integer id){
        findById(id);
        postRepository.deleteById(id);
    }


    public Post fromDTO(PostDTO dto) {
        return new Post(dto.getId(), dto.getTitulo(), dto.getAutor(), dto.getData(), dto.getTexto());
    }
}
