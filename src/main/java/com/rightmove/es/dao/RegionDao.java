package com.rightmove.es.dao;

import com.rightmove.es.domain.Property;
import com.rightmove.es.domain.Region;
import com.vividsolutions.jts.geom.Polygon;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.common.geo.ShapeBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class RegionDao {

    public Collection<Region> listAll() {

        String filename = "regions.xls";
        InputStream xls = getClass().getResourceAsStream("/" + filename);

        HSSFWorkbook wb;
        try {
            wb = new HSSFWorkbook(xls);
        } catch (IOException e) {
            throw new RuntimeException("Problem reading file " + filename, e);
        }

        List<Region> regions = new ArrayList<Region>();

        // there should be only 1 sheet
        HSSFSheet sheet = wb.getSheetAt(0);

        // first row contains headers
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {

            Region region = new Region();
            HSSFRow row = sheet.getRow(j);

            region.setName(row.getCell(0).getStringCellValue());
            ShapeBuilder.PolygonBuilder polygonBuilder = ShapeBuilder.newPolygon();

            polygonBuilder.point(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue())
                    .point(row.getCell(3).getNumericCellValue(), row.getCell(4).getNumericCellValue())
                    .point(row.getCell(5).getNumericCellValue(), row.getCell(6).getNumericCellValue())
                    .point(row.getCell(7).getNumericCellValue(), row.getCell(8).getNumericCellValue());

            region.setPolygon(polygonBuilder.toPolygon());

            regions.add(region);
        }

        return regions;

    }
}
