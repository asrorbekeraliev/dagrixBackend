package uz.tech4ecobackend.service;

import uz.tech4ecobackend.entity.Role;
import uz.tech4ecobackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role create(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }
}
