package com.api.senai.classes;

import jakarta.persistence.*;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String cep;

    private String logradouro;
    private String bairro;
    private String localidade; // cidade/municipio
    private String complemento;
    
    // Transformar em ENUM
    private String uf; // estado

}
