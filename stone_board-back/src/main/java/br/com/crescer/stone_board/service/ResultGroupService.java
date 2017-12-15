/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.ResultGroup;
import br.com.crescer.stone_board.entity.model.ResultGroupModel;
import br.com.crescer.stone_board.entity.model.ResultGroupRegisterModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.ResultGroupRepository;
import br.com.crescer.stone_board.utils.BadRequestException;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcele.dorneles
 */
@Service
public class ResultGroupService {

    @Autowired
    ResultGroupRepository resultGroupRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    PersonComponent personComponent;

    public ResultGroupModel findById(Long id) {
        ResultGroup resultGroup = resultGroupRepository.findOne(id);
        return ResultGroupModel.convertToResultGroupModel(resultGroup);
    }

    public List<ResultGroupModel> findAllResultGroup() {
        List<ResultGroup> results = resultGroupRepository.findAll();
        List<ResultGroupModel> resultsModel = results.stream()
                .map(ResultGroupModel::convertToResultGroupModel)
                .collect(Collectors.toList());
        return resultsModel;
    }
    
    public List<ResultGroupModel> findByBoardId(Long idBoard){
        return resultGroupRepository.findByBoardId(idBoard)
                .stream()
                .map(ResultGroupModel :: convertToResultGroupModel)
                .collect(Collectors.toList());
    }
    
    public void save(ResultGroupRegisterModel resultGroupRegisterModel) {
        Board board = boardRepository.findOne(resultGroupRegisterModel.getId_board());
        ResultGroup resultGroup = ResultGroupRegisterModel.convertToResultGroup(resultGroupRegisterModel, board);
        resultGroupRepository.save(resultGroup);
    }

    public void addCard(Long idResultGroup, Long idCard) {
        Card card = cardRepository.findOne(idCard);
        ResultGroup resultGroup = resultGroupRepository.findOne(idResultGroup);
        card.setResultGroup(resultGroup);
      //  resultGroup.getCards().add(card);
         cardRepository.save(card);
       // resultGroupRepository.save(resultGroup);
    }

    public void update(ResultGroupRegisterModel resultGroupRegisterModel) {
        Person personLoged = personComponent.loggedPersonDetails();
        List<Board> boards = personLoged.getMyBoards();

        if (!boards.stream().anyMatch(b -> b.getId() == resultGroupRegisterModel.getId_board())) {
            throw new BadRequestException("Apenas o usuário que criou o board pode realizar esta ação.");
        }
        ResultGroup resultGroup = resultGroupRepository.findOne(resultGroupRegisterModel.getId());
        resultGroup.setTitle(resultGroupRegisterModel.getTitle());
        resultGroup.setDescription(resultGroupRegisterModel.getDescription());

        resultGroupRepository.save(resultGroup);
    }

    public void delete(Long idResultGroup, Long idBoard) {
        Person personLoged = personComponent.loggedPersonDetails();
        List<Board> boards = personLoged.getMyBoards();

        if (!boards.stream().anyMatch(b -> b.getId() == idBoard)) {
            throw new BadRequestException("Apenas o usuário que criou o board pode realizar esta ação.");
           
        }
            ResultGroup resultGroup = resultGroupRepository.findOne(idResultGroup);
            resultGroupRepository.delete(resultGroup);       
    }

}
