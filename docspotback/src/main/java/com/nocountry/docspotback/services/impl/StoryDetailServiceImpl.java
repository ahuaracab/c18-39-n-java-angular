package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.StoryDetail;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IStoryDetailRepo;
import com.nocountry.docspotback.services.IStoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StoryDetailServiceImpl extends CRUDImpl<StoryDetail, UUID> implements IStoryDetailService {

    @Autowired
    private IStoryDetailRepo repo;

    @Override
    protected IGenericRepo<StoryDetail, UUID> getRepo() {
        return repo;
    }
}
