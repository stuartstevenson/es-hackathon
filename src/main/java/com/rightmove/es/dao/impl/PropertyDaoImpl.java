package com.rightmove.es.dao.impl;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;
import com.rightmove.es.utils.StretchyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class PropertyDaoImpl implements PropertyDao {

	private static Logger log = Logger.getLogger(PropertyDaoImpl.class);
	
	@Override
	public Collection<Property> listAll() {
		String filename = "properties.xls";
		log.info("listing all properties from " + filename);
		InputStream xls = getClass().getResourceAsStream("/" + filename);

		HSSFWorkbook wb;
		try {
			wb = new HSSFWorkbook(xls);
		} catch (IOException e) {
			throw new RuntimeException("Problem reading file " + filename, e);
		}

		ArrayList<Property> properties = new ArrayList<Property>();
		
		// there should be only 1 sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		
		// first row contains headers
		for (int j = 1; j <= sheet.getLastRowNum(); j++) {

			Property property = new Property();
			HSSFRow row = sheet.getRow(j);
			property.setOutcode(row.getCell(0).getStringCellValue());
			property.setIncode(row.getCell(1).getStringCellValue());
			property.setId((long) row.getCell(2).getNumericCellValue());
			property.setPrice((double) row.getCell(3).getNumericCellValue());
			property.setBedrooms((long) row.getCell(4).getNumericCellValue());
			property.setAddress(row.getCell(5).getStringCellValue());
			property.setCity(row.getCell(6).getStringCellValue());
			property.setFirstListingDate(row.getCell(7).getDateCellValue());
			property.setSummary(row.getCell(8).getStringCellValue());
			property.setDescription(row.getCell(9).getStringCellValue());
			property.setPropertyType(row.getCell(10).getStringCellValue());
			property.setPropertySubType(row.getCell(11).getStringCellValue());
			property.setFeatures(Lists.newArrayList(row.getCell(12).getStringCellValue().split("\\^")));

/*			property.setLocation(new GeoPoint(
					row.getCell(13).getNumericCellValue(),
					row.getCell(14).getNumericCellValue()
			));*/

			property.setLocation(StretchyUtils.getRandomLocation());

			property.setImageUrls(Lists.newArrayList(
						row.getCell(15).getStringCellValue().split(";")[1],
						row.getCell(16).getStringCellValue().split(";")[1],
						row.getCell(17).getStringCellValue().split(";")[1]
			));

			property.setNumberOfImages((long) row.getCell(18).getNumericCellValue());
			property.setNumberOfFloorplans((long) row.getCell(19).getNumericCellValue());
			property.setNumberOfVirtualTours((long) row.getCell(20).getNumericCellValue());

			properties.add(property);
		}
		
		return properties;
	}
	
}
