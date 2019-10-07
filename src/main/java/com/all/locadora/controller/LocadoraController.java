package com.all.locadora.controller;

import com.all.locadora.controller.dto.DevolucaoDTO;
import com.all.locadora.controller.dto.LocacaoDTO;
import com.all.locadora.model.LocacaoModel;
import com.all.locadora.service.LocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/locacao")
public class LocadoraController {

    @Autowired
    LocadoraService locadoraService;

    @PostMapping("/locar")
    public ResponseEntity<LocacaoModel> locacaoFilme(@RequestBody @Valid LocacaoDTO locacaoDTO) {
        LocacaoModel locacaoModel = locadoraService.locarFilme(locacaoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(locacaoModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/devolver")
    public ResponseEntity<LocacaoModel> devolucaoFilme(@RequestBody @Valid DevolucaoDTO devolucaoDTO) {
        LocacaoModel locacaoModel = locadoraService.devolverFilme(devolucaoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(locacaoModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
