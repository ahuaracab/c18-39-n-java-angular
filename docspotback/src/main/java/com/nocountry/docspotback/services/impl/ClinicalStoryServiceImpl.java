package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.ClinicalStory;
import com.nocountry.docspotback.repositories.IClinicalStoryRepo;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.services.IClinicalStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClinicalStoryServiceImpl extends CRUDImpl<ClinicalStory, UUID>implements IClinicalStoryService {

    @Autowired
    private IClinicalStoryRepo repo;

    @Override
    protected IGenericRepo<ClinicalStory, UUID> getRepo() {
        return repo;
    }
}
