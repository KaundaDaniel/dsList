package com.devsuperio.dslist.services;


import com.devsuperio.dslist.dto.GameMinDTO;
import com.devsuperio.dslist.entities.Game;
import com.devsuperio.dslist.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
public List<GameMinDTO> findAll(){

    List<Game> result=gameRepository.findAll();
    List<GameMinDTO> dto= result.stream().map(x-> new GameMinDTO(x)).toList();
    return dto;
}
}
