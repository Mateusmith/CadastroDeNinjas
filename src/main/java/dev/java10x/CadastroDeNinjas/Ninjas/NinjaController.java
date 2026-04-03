package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    //  ENDPOINT: GET /ninjas/boasvindas
    //  Descrição: Retorna uma mensagem de boas-vindas
    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }


    //  ENDPOINT: POST /ninjas/criar
    //  Descrição: Cria um novo ninja
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {

        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    //  ENDPOINT: GET /ninjas/listar
    //  Descrição: Lista todos os ninjas cadastrados
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota retorna todos os ninjas cadastrados")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {

        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //  ENDPOINT: GET /ninjas/listar/{id}
    //  Descrição: Busca um ninja pelo ID
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por ID", description = "Rota lista um ninja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " nao existe nos nossos registros");
        }
    }

    //  ENDPOINT: DELETE /ninjas/deletar/{id}
    //  Descrição: Remove um ninja pelo ID
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um ninja", description = "Rota deleta um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {

        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id " + id + " nao encontrado");
        }
    }

    //  ENDPOINT: PATCH /ninjas/alterar/{id}
    //  Descrição: Atualiza os dados de um ninja
    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por ID", description = "Rota altera um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinja(
            @Parameter(description = "Usuario manda o ID no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisicao")
            @RequestBody NinjaDTO ninjaAtualizado) {

        return ninjaService.atualizarNinja(id, ninjaAtualizado)
                .<ResponseEntity<?>>map(ninja -> ResponseEntity.ok(ninja))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ninja com o id: " + id + " nao existe nos nossos registros"));
    }
}