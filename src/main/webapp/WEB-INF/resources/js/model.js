define(["backbone"], function(Backbone){
	return Backbone.Model.extend({
		getSearchParams: function(){
			var params = {};
			if(this.get("searchPhrase")){
				params.searchPhrase = this.get("searchPhrase");
			}
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
		}
	});
});