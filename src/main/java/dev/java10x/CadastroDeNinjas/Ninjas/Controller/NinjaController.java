package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ═══════════════════════════════════════════════════════════════════════════
//  CONTROLLER — Controller.java
//
//  O QUE É UM CONTROLLER?
//  É o intermediário entre o usuário e o banco de dados.
//  O usuário nunca fala diretamente com o banco — o Controller filtra tudo.
//
//  Analogia: como o app do Nubank é o intermediário entre você e o banco
//  de dados do Nubank. Você pede o saldo → app (Controller) → banco de dados.
//
//  @RestController
//  Diz ao Spring: "esta classe é um Controller de API REST."
//  O Spring a registra automaticamente no mapa de rotas ao iniciar.
//  Combina @Controller + @ResponseBody por baixo dos panos.
// ═══════════════════════════════════════════════════════════════════════════

// @RestController = esta classe é um Controller de API REST
// O Spring a reconhece e registra automaticamente
@RestController

// @RequestMapping("/") = prefixo base de todas as rotas desta classe
// Boa prática: sempre colocar "/" para separar corretamente as rotas
@RequestMapping("/")
public class NinjaController {

    // @GetMapping("/boasvindas") = rota GET acessível em localhost:8080/boasvindas
    // Quando alguém acessa essa URL, o Spring chama este método e retorna o resultado
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }
}