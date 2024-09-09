package com.git.mwacha.infra.api.controller;

import com.git.mwacha.application.analysis.ResultService;
import com.git.mwacha.infra.api.controller.streams.ResultResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
public class ResultAnalysisController {
  private final ResultService service;

  int count = 0;

  @PostMapping(value = "/results")
  @Operation(description = "Processa os resultador por ids.")
  public List<ResultResponse> process(@RequestBody List<Long> ids) {

    return service.results(ids);
  }

  @GetMapping("/{tenant}")
  @Operation(description = "Retorna o resultado pelo tenant id.")
  public String result(@PathVariable String tenant) {
    System.out.println(++count);
    return "Test tenant = " + tenant;
  }

  @GetMapping
  @Operation(description = "Obtém um item pelo header.")
  public ResponseEntity<String> getItemByHeader(@RequestHeader("x-tenant") String customHeader) {
    // Imprime o valor do header
    System.out.println("Custom-Header value: " + customHeader);

    return ResponseEntity.ok("Header Value: " + customHeader);
  }
}
