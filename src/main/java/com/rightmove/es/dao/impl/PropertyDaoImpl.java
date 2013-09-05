package com.rightmove.es.dao.impl;

import com.rightmove.es.dao.PropertyDao;
import com.rightmove.es.domain.Property;
import com.rightmove.es.utils.StretchyUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.elasticsearch.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class PropertyDaoImpl implements PropertyDao {

	private static Logger log = Logger.getLogger(PropertyDaoImpl.class);

	public Collection<Property> loadPropertiesFromDBAndSaveToJSON() {

		int file = 1;

		Connection connection = null;

		Collection<Property> properties = new ArrayList<>();

		try {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader("I:\\connection.properties"));
				String url = bufferedReader.readLine();
				String user = bufferedReader.readLine();
				String pass = bufferedReader.readLine();
				connection = DriverManager.getConnection(url, user, pass);
			} catch (IOException e) {
				throw new RuntimeException("Connection Properties Does Not Exist.");
			}

			ResultSet resultSet = connection.createStatement().executeQuery(
					"select * from ferguss.properties\n" +
					"where main_image_1 is not null\n" +
					"and main_image_2 is not null\n" +
					"and main_image_3 is not null\n" +
					"and address is not null\n" +
					"and city is not null\n" +
					"and features is not null\n");

			while (resultSet.next()) {
				Property property = new Property();
				property.setOutcode(resultSet.getString(1));
				property.setIncode(resultSet.getString(2));
				property.setId(resultSet.getLong(3));
				property.setPrice(resultSet.getDouble(4));
				property.setBedrooms(resultSet.getLong(5));
				property.setAddress(resultSet.getString(6));
				property.setCity(resultSet.getString(7));
				property.setFirstListingDate(resultSet.getDate(8));
				property.setSummary(resultSet.getString(9));
				property.setDescription(resultSet.getString(10));
				property.setPropertyType(resultSet.getString(11));
				property.setPropertySubType(resultSet.getString(12));

				String features = resultSet.getString("features");
				if(features != null) {
					property.setFeatures(Lists.newArrayList(features.split("\\^")));
				}

				property.setImageUrls(Lists.newArrayList(
						resultSet.getString(16).split(";")[1],
						resultSet.getString(17).split(";")[1],
						resultSet.getString(18).split(";")[1]
				));

				property.setNumberOfImages(resultSet.getLong(19));
				property.setNumberOfFloorplans(resultSet.getLong(20));
				property.setNumberOfVirtualTours(resultSet.getLong(21));
				property.setBoost(StretchyUtils.generateBoost());

				properties.add(property);

				if(properties.size() == 10000) {
					savePropertiesToFile(file++, properties);
					properties.clear();
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return properties;
	}

	private void savePropertiesToFile(int file, Collection<Property> properties) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			new FileOutputStream("I:\\properties\\properties" + file + ".json").write(mapper.writeValueAsBytes(properties));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Property> listAll() {
		ObjectMapper mapper = new ObjectMapper();
		Collection<Property> properties = new ArrayList<>();
		for(File file : StretchyUtils.getAllJSONFiles()) {
			try {
				properties.addAll((Collection<? extends Property>) mapper.reader(Collection.class).readValue(file));
			} catch (IOException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
		return properties;
	}

	@Override
	public Collection<Property> listAllByFile(File file) {
		ObjectMapper mapper = new ObjectMapper();
		Collection<Property> properties = new ArrayList<>();
		try {
			properties.addAll((Collection<? extends Property>) mapper.readValue(file, new TypeReference<Collection<Property>>(){}));
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return properties;
	}
}
