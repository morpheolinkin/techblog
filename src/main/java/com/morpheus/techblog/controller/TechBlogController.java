package com.morpheus.techblog.controller;

import com.morpheus.techblog.dto.PostDTO;
import com.morpheus.techblog.model.Post;
import com.morpheus.techblog.service.TechBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/blog")
public class TechBlogController {

    @Autowired
    private TechBlogService blogService;

    //=================================  REST CONTROLLER  ========================================
    /*@GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> postList = blogService.findAll();
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findByIdPost(@PathVariable Integer id) {
        Post byId = blogService.findById(id);
        return ResponseEntity.ok().body(byId);
    }

    @PostMapping
    public ResponseEntity<Void> insertPost(@Valid @RequestBody Post post) {
        Post save = blogService.save(post);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }*/

    //=================================  CONTROLLER COM MODEL_AND_VIEW ========================================

    @GetMapping(value = "/posts")
    public ModelAndView getPosts() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> list = blogService.findAll();
        List<PostDTO> dtoList = list
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());

        mv.addObject("posts", dtoList);
        return mv;
    }

    @GetMapping(value = "/posts/{id}")
    public ModelAndView getPostDetails(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("postDatails");
        Post byId = blogService.findById(id);
        mv.addObject("postById", byId);
        return mv;
    }

    @GetMapping(value = "/newpost")
    public String getPostForm() {
        return "postForm";
    }

    @PostMapping(value = "/newpost")
    public String savePost(@Valid PostDTO dto,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute(
                    "mensagem",
                    "Verifique se os campos obrigatórios foram preenchidos");
            return "redirect:/blog/newpost";
        }

        dto.setData(LocalDate.now());
        Post post = blogService.fromDTO(dto);
        blogService.save(post);

        return "redirect:/blog/posts";
    }

    @GetMapping(value = "/{id}")
    public String deletePost(@PathVariable Integer id){
        blogService.delete(id);
        return "redirect:/blog/posts";
    }
}
