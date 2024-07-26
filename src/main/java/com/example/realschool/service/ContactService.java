package com.example.realschool.service;

import com.example.realschool.constants.RealSchoolConstants;
import com.example.realschool.model.Contact;
import com.example.realschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(RealSchoolConstants.OPEN);

        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }
    }


    public boolean updateMsgStatus(int ContactId) {
        boolean isUpdated = false;

        Optional<Contact> contact = contactRepository.findById(ContactId);
        contact.ifPresent(value -> {
            value.setStatus(RealSchoolConstants.CLOSE);
        });

        Contact updatedContact = contactRepository.save(contact.get());
        if (updatedContact.getContactId() > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findByStatus(RealSchoolConstants.OPEN);
    }
}
