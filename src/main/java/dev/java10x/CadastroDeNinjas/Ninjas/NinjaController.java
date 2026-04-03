package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {

        // pegar json vamos serializar json, e salvar ele dentro do banco de dados
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " nao existe nos nossos registros");
        }
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {

        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id " + id + " nao encontrado");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {

        return ninjaService.atualizarNinja(id, ninjaAtualizado)
                .<ResponseEntity<?>>map(ninja -> ResponseEntity.ok(ninja))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ninja com o id: " + id + " nao existe nos nossos registros"));
    }
}