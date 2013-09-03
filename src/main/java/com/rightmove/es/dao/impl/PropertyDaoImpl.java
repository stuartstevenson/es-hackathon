package com.rightmove.es.dao.impl;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.shape.impl.PointImpl;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
			property.setSummary(row.getCell(3).getStringCellValue());
			property.setPropertyType(row.getCell(4).getStringCellValue());
			property.setPropertySubType(row.getCell(5).getStringCellValue());

			List<String> features = new ArrayList<>();

			for(String feature : row.getCell(6).getStringCellValue().split("^")) {
				features.add(feature);
			}



			property.setFeatures(features);
			property.setPoint(new PointImpl(
					row.getCell(7).getNumericCellValue(),
					row.getCell(8).getNumericCellValue(),
					SpatialContext.GEO
					));
			
			properties.add(property);
		}
		
		return properties;
	}
	
}
