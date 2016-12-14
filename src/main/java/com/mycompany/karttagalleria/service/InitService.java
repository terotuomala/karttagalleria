package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.CoordinateSystem;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import com.mycompany.karttagalleria.repository.RoleRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.mycompany.karttagalleria.repository.AccountRepository;

/**
 *
 * @author Tero Tuomala
 */

//@Profile("default")
@Service
public class InitService {
    
    @Autowired
    AccountRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;
    
    @PostConstruct
    public void init() {
        // Create role 'USER'
        Role roleUser = new Role();
        roleUser.setName("USER");
        roleRepository.save(roleUser);
        
        // Create role 'PUBLISHER'
        Role rolePublisher = new Role();
        rolePublisher.setName("PUBLISHER");
        roleRepository.save(rolePublisher);
        
        // Create role 'ADMIN'
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleAdmin);
        
        // Create user 'user' and assign role 'roleUser' to it
        Account userUser = new Account();
        userUser.setUsername("user");
        userUser.setPassword("password1234");
        userUser.setRole(roleUser);
        userRepository.save(userUser);
        
        // Create user 'publisher' and assign role 'rolePublisher' to it
        Account publisherUser = new Account();
        publisherUser.setUsername("publisher");
        publisherUser.setPassword("password1234");
        publisherUser.setRole(rolePublisher);
        userRepository.save(publisherUser);
        
        // Create user 'admin' and assign role 'roleAdmin' to it
        Account adminUser = new Account();
        adminUser.setUsername("admin");
        adminUser.setPassword("password1234");
        adminUser.setRole(roleAdmin);
        userRepository.save(adminUser);
        
        // Create category 'Kategoria I'
        Category categoryFirst = new Category();
        categoryFirst.setName("Kategoria I");
        categoryRepository.save(categoryFirst);
        
        // Create category 'Kategoria II'
        Category categorySecond = new Category();
        categorySecond.setName("Kategoria II");
        categoryRepository.save(categorySecond);
        
        // Create coordinateSystem 'WGS 84 (EPSG:4326)'
        CoordinateSystem coordinateSystemFirst = new CoordinateSystem();
        coordinateSystemFirst.setName("WGS 84 (EPSG:4326)");
        coordinateSystemRepository.save(coordinateSystemFirst);
        
        // Create coordinateSystem 'ETRS89 (EPSG:4258)'
        CoordinateSystem coordinateSystemSecond = new CoordinateSystem();
        coordinateSystemSecond.setName("ETRS89 (EPSG:4258)");
        coordinateSystemRepository.save(coordinateSystemSecond);
        
        // Create map 'Karttasovellus I' and assign category 'categoryFirst' to it
        Map map = new Map();
        map.setTitle("Karttasovellus I");
        map.setCategory(categoryFirst);
        map.setDescription("Karttasovelluksella I voidaan tehdä sitä sun tätä.");
        map.setCoordinateSystem(coordinateSystemFirst);
        map.setUrl("http://www.google.com");
        mapRepository.save(map);
        
        // Create map 'Karttasovellus II' and assign category 'categorySecond' to it
        Map map2 = new Map();
        map2.setTitle("Karttasovellus II");
        map2.setCategory(categorySecond);
        map2.setDescription("Karttasovelluksella II voidaan tehdä sitä sun tätä.");
        map2.setCoordinateSystem(coordinateSystemSecond);
        map2.setUrl("http://www.google.com");
        mapRepository.save(map2);
    }
}
