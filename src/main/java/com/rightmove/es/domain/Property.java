package com.rightmove.es.domain;

import org.apache.commons.lang.StringUtils;
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
	private Date firstListingDate;
	private String incode;
	private String outcode;
	private String summary;
	private String propertyType;
	private String propertySubType;
	private List<String> features;
	private List<String> imageUrls;
	private Long numberOfImages;
	private Long numberOfFloorplans;
	private Long numberOfVirtualTours;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Property)) return false;

		Property property = (Property) o;

		if (features != null ? !features.equals(property.features) : property.features != null) return false;
		if (firstListingDate != null ? !firstListingDate.equals(property.firstListingDate) : property.firstListingDate != null) {
			return false;
		}
		if (id != null ? !id.equals(property.id) : property.id != null) return false;
		if (imageUrls != null ? !imageUrls.equals(property.imageUrls) : property.imageUrls != null) return false;
		if (incode != null ? !incode.equals(property.incode) : property.incode != null) return false;
		if (numberOfFloorplans != null ? !numberOfFloorplans.equals(property.numberOfFloorplans) : property.numberOfFloorplans != null) {
			return false;
		}
		if (numberOfImages != null ? !numberOfImages.equals(property.numberOfImages) : property.numberOfImages != null) {
			return false;
		}
		if (numberOfVirtualTours != null ? !numberOfVirtualTours.equals(property.numberOfVirtualTours) : property.numberOfVirtualTours != null) {
			return false;
		}
		if (outcode != null ? !outcode.equals(property.outcode) : property.outcode != null) return false;
		if (price != null ? !price.equals(property.price) : property.price != null) return false;
		if (propertySubType != null ? !propertySubType.equals(property.propertySubType) : property.propertySubType != null) {
			return false;
		}
		if (propertyType != null ? !propertyType.equals(property.propertyType) : property.propertyType != null) {
			return false;
		}
		if (summary != null ? !summary.equals(property.summary) : property.summary != null) return false;
		if (version != null ? !version.equals(property.version) : property.version != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (firstListingDate != null ? firstListingDate.hashCode() : 0);
		result = 31 * result + (incode != null ? incode.hashCode() : 0);
		result = 31 * result + (outcode != null ? outcode.hashCode() : 0);
		result = 31 * result + (summary != null ? summary.hashCode() : 0);
		result = 31 * result + (propertyType != null ? propertyType.hashCode() : 0);
		result = 31 * result + (propertySubType != null ? propertySubType.hashCode() : 0);
		result = 31 * result + (features != null ? features.hashCode() : 0);
		result = 31 * result + (imageUrls != null ? imageUrls.hashCode() : 0);
		result = 31 * result + (numberOfImages != null ? numberOfImages.hashCode() : 0);
		result = 31 * result + (numberOfFloorplans != null ? numberOfFloorplans.hashCode() : 0);
		result = 31 * result + (numberOfVirtualTours != null ? numberOfVirtualTours.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", incode=" + incode + ", outcode="
				+ outcode + ", summary=" + StringUtils.substring(summary, 0, 7) + "...]"; // StringUtils avoids null pointer
	}

}
