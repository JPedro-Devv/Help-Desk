package com.jpdev.help_desk.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tb_usuarios")
/**
 * Especifica a estratégia de mapeamento de herança para a hierarquia de classes de entidade 
 * que deriva da classe de entidade anotada.
 *  
 *  Esta anotação deve ser aplicada à classe de entidade que constitui a raiz da hierarquia 
 *  de classes de entidade. Se a anotação `Inheritance` não for especificada, ou se nenhum 
 *  tipo de herança for definido para uma hierarquia de classes de entidade, a estratégia de 
 *  mapeamento `SINGLE_TABLE` será utilizada. 
 */
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;
        
    /**
     * Declara uma coleção de instâncias de um tipo básico ou de uma classe incorporável.
     * Deve ser especificada se a coleção for mapeada por meio de uma tabela de coleção.
     * 
     * A anotação CollectionTable especifica um mapeamento para uma tabela de banco de dados.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    /**
     * Especifica a tabela utilizada para o mapeamento de coleções de tipos básicos ou 
     * incorporáveis ​​(*embeddable*). 
     * 
     * Aplicado ao campo ou à propriedade que contém a coleção.
     */
    @CollectionTable(
        name = "tb_usuarios_perfis", 
        joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private Set<Perfil> perfis = new HashSet<>();

    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void  setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfils() {
        return perfis;
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil);
    }
    
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream()
            .map(role -> new SimpleGrantedAuthority(role.name()))
            .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
