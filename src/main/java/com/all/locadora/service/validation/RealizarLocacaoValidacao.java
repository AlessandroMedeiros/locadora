package com.all.locadora.service.validation;

import com.all.locadora.controller.dto.LocacaoDTO;
import com.all.locadora.controller.exception.FieldMessage;
import com.all.locadora.model.FilmeModel;
import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.FilmeRepository;
import com.all.locadora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RealizarLocacaoValidacao implements ConstraintValidator<RealizarLocacao, LocacaoDTO> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public void initialize(RealizarLocacao constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocacaoDTO locacaoDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (locacaoDTO.getIdUsuario() == null) {
            list.add(new FieldMessage("idUsuario", "Usuário não informado"));
        }

        Optional<UsuarioModel> usuario = usuarioRepository.findById(locacaoDTO.getIdUsuario());
        if (!usuario.isPresent()) {
            list.add(new FieldMessage("idUsuario", "Usuário não encontrado"));
        }

        if (locacaoDTO.getIdFilme() == null) {
            list.add(new FieldMessage("idFilme", "Filme inválido"));
        }

        Optional<FilmeModel> filme = filmeRepository.findById(locacaoDTO.getIdFilme());

        if (!filme.isPresent()) {
            list.add(new FieldMessage("idFilme", "Filme não encontrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
