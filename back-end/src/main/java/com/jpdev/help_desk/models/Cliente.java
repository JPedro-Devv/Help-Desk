package com.jpdev.help_desk.models;

import java.util.UUID;

import com.jpdev.help_desk.models.ENUNs.Setor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tb_clientes")
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
   
    @Column(nullable = false, unique = true)    
    private String cpf;

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private Setor setor;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    public Cliente() {}

    public Cliente(String cpf, Setor setor, Usuario usuario) {
        this.cpf = cpf;
        this.setor = setor;
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
   
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
