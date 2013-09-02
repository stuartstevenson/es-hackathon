package com.rightmove.es.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rightmove.es.dataprovider.PropertyDataProvider;
import com.rightmove.es.domain.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class PropertyRepositoryTest {

    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private PropertyDataProvider propertyService;

    @Test
    public void indexAndRetrieveRows() {
        Collection<Property> propertyCollection = propertyService.listAll();

        propertyRepository.save(propertyCollection);

        assertEquals("Expect 10 records for paging result",10, propertyRepository.findAll(new PageRequest(0, 10)).getNumberOfElements());
    }
}
