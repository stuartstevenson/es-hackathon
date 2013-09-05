define(["backbone", "underscore"], function(Backbone, _){
	return Backbone.Model.extend({
		defaults: {
			fieldOrderBy: [
				{
					name: "Relevance",
					value: "_score",
					enabled: true
				},
				{
					name: "Price",
					value: "price",
					enabled: false
				}
			],
			directionOrderBy: [
				{
					name: "Descending",
					value: "desc",
					enabled: true
				},
				{
					name: "Ascending",
					value: "asc",
					enabled: false
				}
			]
		},
		getSearchParams: function(){
			var params = {};
			if(this.get("searchPhrase")){
				params.searchPhrase = this.get("searchPhrase");
			}
			params.fieldOrderBy = _.findWhere(this.get("fieldOrderBy"), {enabled: true}).value;
			params.directionOrderBy = _.findWhere(this.get("directionOrderBy"), {enabled: true}).value;
			if(this.get("filters.outcode")){
				params.outcode = this.get("filters.outcode").join(",");
			}
			if(this.get("filters.incode")){
				params.incode = this.get("filters.incode").join(",");
			}
			if(this.get("filters.city")){
				params.city = this.get("filters.city").join(",");
			}
			if(this.get("filters.propertyType")){
				params.propertyType = this.get("filters.propertyType").join(",");
			}
			if(this.get("filters.propertySubType")){
				params.propertySubType = this.get("filters.propertySubType").join(",");
			}
			return params;
		},
		getSearchParamsForURL: function(){
			var params = this.getSearchParams();
			return _.map(params, function(value, key){
				return key + "=" + value;
			}).join("&");
		},
		clearFilters: function(){
			this.unset("filters.outcode");
			this.unset("filters.incode");
			this.unset("filters.city");
			this.unset("filters.propertyType");
			this.unset("filters.propertySubType");
		}
	});
});