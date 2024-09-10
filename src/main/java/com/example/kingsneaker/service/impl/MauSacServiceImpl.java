package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.MauSac;
import com.example.kingsneaker.repository.MauSacRepository;
import com.example.kingsneaker.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;
    @Override
    public void save(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void deleteById(Long id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public MauSac findById(Long id) {
        return mauSacRepository.findById(id).orElse(null);
    }

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }
}
