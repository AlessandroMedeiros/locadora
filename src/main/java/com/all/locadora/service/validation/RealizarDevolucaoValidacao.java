package com.all.locadora.service.validation;

import com.all.locadora.controller.dto.DevolucaoDTO;
import com.all.locadora.controller.exception.FieldMessage;
import com.all.locadora.model.LocacaoModel;
import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.LocacaoRepository;
import com.all.locadora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RealizarDevolucaoValidacao implements ConstraintValidator<RealizarDevolucao, DevolucaoDTO> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Override
    public void initialize(RealizarDevolucao constraintAnnotation) {

    }

    @Override
    public boolean isValid(DevolucaoDTO devolucaoDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (devolucaoDTO.getIdUsuario() == null) {
            list.add(new FieldMessage("idUsuario", "Usuário não informado"));
        }

        Optional<UsuarioModel> usuario = usuarioRepository.findById(devolucaoDTO.getIdUsuario());
        if (!usuario.isPresent()) {
            list.add(new FieldMessage("idUsuario", "Usuário não encontrado"));
        }

        if (devolucaoDTO.getIdLocacao() == null) {
            list.add(new FieldMessage("idLocacao", "Locação não informado"));
        }

        Optional<LocacaoModel> locacaoModel = locacaoRepository.findById(devolucaoDTO.getIdLocacao());
        if (!locacaoModel.isPresent()) {
            list.add(new FieldMessage("idLocacao", "Locacao não encontrada"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
