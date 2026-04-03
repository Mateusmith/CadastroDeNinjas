package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // @GetMapping("/boasvindas") = rota GET acessível em localhost:8080/boasvindas
    // Quando alguém acessa essa URL, o Spring chama este método e retorna o resultado
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }

    /// Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {
        // pegar json vamos serializar json, e salvar ele dentro do banco de dados
        return ninjaService.criarNinja(ninja);
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaDTO alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }
}