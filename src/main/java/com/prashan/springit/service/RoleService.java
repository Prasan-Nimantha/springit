package com.prashan.springit.service;

import com.prashan.springit.model.Role;
import com.prashan.springit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
