package com.moviestorage.moviestorage.service;

import com.moviestorage.moviestorage.repository.ActorRepository;
import com.moviestorage.moviestorage.vo.ActorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<ActorVO> findAll() {
        return new ArrayList<>(actorRepository.findAll());
    }

    public ActorVO findById(Long id) {
        return actorRepository.findActorById(id);
    }

    public ActorVO findById(String sId) {
        long id = Long.parseLong(sId);
        return actorRepository.findActorById(id);
    }

    public List<ActorVO> findByName(String name) {
        return new ArrayList<>(actorRepository.findActorByName(name));
    }


    public ActorVO save(ActorVO actorVO) {
        actorRepository.save(actorVO);
        return actorVO;
    }

    public void update(ActorVO actorVO) {
        if (actorVO.getId() == 0L)
            save(actorVO);
        ActorVO actor = actorRepository.findActorById(actorVO.getId());

        if (actor != null) {
//            actorRepository.save(actor);
            actorRepository.save(actorVO);
        }
    }



}
