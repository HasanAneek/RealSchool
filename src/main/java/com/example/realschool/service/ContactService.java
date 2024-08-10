package com.example.realschool.service;

import com.example.realschool.constants.RealSchoolConstants;
import com.example.realschool.model.Contact;
import com.example.realschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc")
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        return contactRepository.findByStatus(RealSchoolConstants.OPEN, pageable);
    }


    public void updateMsgStatus(int contactId) {
        boolean isUpdated = false;

        int rows = contactRepository.updateStatusById(RealSchoolConstants.CLOSE, contactId);
        if (rows > 0) {
            isUpdated = true;
        }
    }
}
