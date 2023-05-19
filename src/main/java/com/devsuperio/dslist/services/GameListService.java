package com.devsuperio.dslist.services;

import com.devsuperio.dslist.dto.GameListDTO;
import com.devsuperio.dslist.entities.GameList;
import com.devsuperio.dslist.projection.GameMinProjection;
import com.devsuperio.dslist.repository.GameListRepository;
import com.devsuperio.dslist.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameListRepository gameListRepository;
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result= gameListRepository.findAll();
        return result.stream().map(x->new GameListDTO(x)).toList();
    }
    @Transactional(readOnly = true)
    public void move(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list= gameRepository.searchByList(listId);
        GameMinProjection obj= list.remove(sourceIndex);
        list.add(destinationIndex, obj);
        int min= sourceIndex<destinationIndex?sourceIndex:destinationIndex;
        int max=sourceIndex<destinationIndex? destinationIndex:sourceIndex;

        for (int k=min; k<=max; k++){
            gameListRepository.updateBelongingPosition(listId, list.get(k).getId(), k);
        }
    }
}
