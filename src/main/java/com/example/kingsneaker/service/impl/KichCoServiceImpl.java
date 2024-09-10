package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.KichCo;
import com.example.kingsneaker.repository.KichCoRepository;
import com.example.kingsneaker.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    KichCoRepository kichCoRepository;
    @Override
    public void save(KichCo kichCo) {
        kichCoRepository.save(kichCo);
    }

    @Override
    public void deleteById(Long id) {
        kichCoRepository.deleteById(id);
    }

    @Override
    public KichCo findById(Long id) {
        return kichCoRepository.findById(id).orElse(null);
    }

    @Override
    public List<KichCo> findAll() {
        return kichCoRepository.findAll();
    }
}
