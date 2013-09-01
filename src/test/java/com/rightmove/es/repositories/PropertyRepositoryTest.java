package com.rightmove.es.repositories;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class PropertyRepositoryTest {

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    public void indexAndRetrieveRows() {
        Collection<Property> propertyCollection = new PropertyDao().listAll();

        propertyRepository.save(propertyCollection);

        assertEquals("Expect 10 records for paging result",10, propertyRepository.findAll(new PageRequest(0, 10)).getNumberOfElements());
    }
}
