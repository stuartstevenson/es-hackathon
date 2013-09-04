package com.rightmove.es.domain;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "property-search-index", type = "rm-property")
public class Property {

    @Id
	private Long id;
    @Version
    private Long version;
	private Long price;
	private Long bedrooms;
	private String address;
	private String city;
	private Date firstListingDate;
	private String incode;
	private String outcode;
	private GeoPoint location;
	private String summary;
	private String description;
	private String propertyType;
	private String propertySubType;
	private List<String> features;
	private List<String> imageUrls;
	private Long numberOfImages;
	private Long numberOfFloorplans;
	private Long numberOfVirtualTours;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNumberOfImages() {
		return numberOfImages;
	}

	public void setNumberOfImages(Long numberOfImages) {
		this.numberOfImages = numberOfImages;
	}

	public Long getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(Long bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Long getNumberOfFloorplans() {
		return numberOfFloorplans;
	}

	public void setNumberOfFloorplans(Long numberOfFloorplans) {
		this.numberOfFloorplans = numberOfFloorplans;
	}

	public Long getNumberOfVirtualTours() {
		return numberOfVirtualTours;
	}

	public void setNumberOfVirtualTours(Long numberOfVirtualTours) {
		this.numberOfVirtualTours = numberOfVirtualTours;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getFirstListingDate() {
		return firstListingDate;
	}

	public void setFirstListingDate(Date firstListingDate) {
		this.firstListingDate = firstListingDate;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getIncode() {
		return incode;
	}
	public void setIncode(String incode) {
		this.incode = incode;
	}
	public String getOutcode() {
		return outcode;
	}
	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertySubType() {
		return propertySubType;
	}

	public void setPropertySubType(String propertySubType) {
		this.propertySubType = propertySubType;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}

	public GeoPoint getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", version=" + version + ", price="
				+ price + ", bedrooms=" + bedrooms + ", address=" + address
				+ ", firstListingDate=" + firstListingDate + ", incode="
				+ incode + ", outcode=" + outcode + ", city=" + city
				+ ", summary=" + summary + ", description=" + description
				+ ", propertyType=" + propertyType + ", propertySubType="
				+ propertySubType + ", features=" + features + ", imageUrls="
				+ imageUrls + ", numberOfImages=" + numberOfImages
				+ ", numberOfFloorplans=" + numberOfFloorplans
				+ ", numberOfVirtualTours=" + numberOfVirtualTours + "]";
	}

}
