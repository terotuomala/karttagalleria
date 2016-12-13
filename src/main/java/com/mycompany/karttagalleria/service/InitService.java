package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.domain.User;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.repository.UserRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tero Tuomala
 */

@Service
public class InitService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @PostConstruct
    public void init() {
        Role roleUser = new Role();
        roleUser.setName("USER");
        roleRepository.save(roleUser);
        
        Role rolePublisher = new Role();
        rolePublisher.setName("PUBLISHER");
        roleRepository.save(rolePublisher);
        
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleAdmin);
        
        User adminUser = new User();
        adminUser.setUsername("user");
        adminUser.setPassword("password1234");
        adminUser.setRole(roleAdmin);
        userRepository.save(adminUser);
        
        Category categoryFirst = new Category();
        categoryFirst.setName("Kategoria I");
        categoryRepository.save(categoryFirst);
        
        Category categorySecond = new Category();
        categorySecond.setName("Kategoria II");
        categoryRepository.save(categorySecond);
        
        
        Map map = new Map();
        map.setTitle("Karttasovellus I");
        map.setCategory(categoryFirst);
        map.setDescription("Karttasovelluksella X voidaan tehdä sitä ja tätä.");
        map.setUrl("http://www.google.com");
        mapRepository.save(map);
        
        Map map2 = new Map();
        map2.setTitle("KarttasovellusY");
        map2.setCategory(categorySecond);
        map2.setDescription("Karttasovelluksella Y voidaan tehdä sitä ja tätä.");
        map2.setUrl("http://www.google.com");
        mapRepository.save(map2);
    }
}
