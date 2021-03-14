package com.morpheus.techblog.dto;

import com.morpheus.techblog.model.Post;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

public class PostDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Verifique se os campos obrigatórios foram preenchidos")
    private String titulo;
    @NotEmpty(message = "Verifique se os campos obrigatórios foram preenchidos")
    private String autor;
    private LocalDate data;

    @NotEmpty(message = "Verifique se os campos obrigatórios foram preenchidos")
    private String texto;

    public PostDTO() {
    }

    public PostDTO(Post obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.autor = obj.getAutor();
        this.data = obj.getData();
        this.texto = obj.getTexto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
