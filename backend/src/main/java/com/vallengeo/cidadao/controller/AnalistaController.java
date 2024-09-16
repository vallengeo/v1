package com.vallengeo.cidadao.controller;

import com.vallengeo.cidadao.payload.request.ProcessoArquivarRequest;
import com.vallengeo.cidadao.payload.response.FichaImovelAnalistaResponse;
import com.vallengeo.cidadao.payload.response.UltimoProcessoResponse;
import com.vallengeo.cidadao.payload.response.cadastro.ProcessoResponse;
import com.vallengeo.cidadao.service.ImovelService;
import com.vallengeo.cidadao.service.ProcessoService;
import com.vallengeo.core.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static com.vallengeo.core.util.Constants.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/analista")
@Tag(name = "Analista", description = "Operação do analista")
@SecurityRequirement(name = "bearerAuth")
public class AnalistaController {
    private final HttpServletRequest request;
    private final ImovelService imovelService;
    private final ProcessoService processoService;

    @Operation(summary = "Serviço de arquivamento do imóvel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SALVO_SUCESSO, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", description = UNAUTHORIZED_ERROR),
            @ApiResponse(responseCode = "403", description = FORBIDDEN_ERROR),
            @ApiResponse(responseCode = "422", description = ENTITY_VALITATION_ERROR),
            @ApiResponse(responseCode = "500", description = GENERAL_ERROR)
    })
    @PreAuthorize("hasRole('IMOVEL_LISTA_IMOVEL_ARQUIVAR') or hasRole('HOME_RESUMO_IMOVEL_ARQUIVAR')")
    @PostMapping(value = "/imovel/arquivar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcessoResponse> Arquivar(@Validated @RequestBody ProcessoArquivarRequest input) {
        log.info("Arquivando processo {}", input.getIdProcesso());
        return ResponseEntity.status(201).body(processoService.arquivar(input));
    }

    @PreAuthorize("hasRole('IMOVEL_LISTA_IMOVEL_VISUALIZAR')")
    @Operation(summary = "Informações referente a ficha imobiliária pelo identificador do processo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = Constants.ENTITY_NOT_FOUND_ERROR)
    })
    @GetMapping(value = "/imovel/ficha/{processoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FichaImovelAnalistaResponse> fichaImovelAnalistaPeloProcessoId(@PathVariable UUID processoId) {
        return ResponseEntity.ok(imovelService.fichaImovelAnalista(processoId));
    }

    @PreAuthorize("hasRole('HOME_ULTIMO_PROCESSO_VISUALIZAR')")
    @Operation(summary = "Lista os ultimos processos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = Constants.ENTITY_NOT_FOUND_ERROR)
    })
    @GetMapping(value = "/processo/ultimos-cadastrados", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UltimoProcessoResponse>> buscarUltimosProcessosCadastrados(@RequestParam(value = "pagina") int pagina,
                                                                                          @RequestParam(value = "itens-por-pagina") int itensPorPagina) {
        return ResponseEntity.ok(processoService.buscarUltimosProcessosCadastrados(pagina, itensPorPagina, request));
    }

    @PreAuthorize("hasRole('HOME_ATUALIZACAO_PROCESSO_VISUALIZAR')")
     @Operation(summary = "Lista os ultimos processos alterados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = Constants.ENTITY_NOT_FOUND_ERROR)
    })
    @GetMapping(value = "/processo/ultimos-alterados", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UltimoProcessoResponse>> buscarUltimosProcessosAlterados(@RequestParam(value = "pagina") int pagina,
                                                                                          @RequestParam(value = "itens-por-pagina") int itensPorPagina) {
        return ResponseEntity.ok(processoService.buscarUltimosProcessosAlterados(pagina, itensPorPagina, request));
    }
}