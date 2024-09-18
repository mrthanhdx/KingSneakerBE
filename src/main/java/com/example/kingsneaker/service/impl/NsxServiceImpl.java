package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.NSX;
import com.example.kingsneaker.repository.NsxRepository;
import com.example.kingsneaker.service.NsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NsxServiceImpl implements NsxService {

    @Autowired
    NsxRepository nsxRepository;
    @Override
    public void save(NSX nsx) {
        nsxRepository.save(nsx);

        System.out.println();
    }

    @Override
    public void deleteById(Long id) {
        nsxRepository.deleteById(id);
    }

    @Override
    public NSX findById(Long id) {
        return nsxRepository.findById(id).orElse(null);
    }

    @Override
    public List<NSX> findAll() {
        return nsxRepository.findAll();
    }
}
