package com.example.realschool.service;

import com.example.realschool.constants.RealSchoolConstants;
import com.example.realschool.model.Person;
import com.example.realschool.model.Roles;
import com.example.realschool.repository.PersonRepository;
import com.example.realschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(RealSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
