define(["backbone", "underscore"], function (Backbone, _) {
	return Backbone.Router.extend({
		initialize: function(options){
			this.options = options;
			_.bindAll(this, "handleSearchResponse");
			Backbone.history.start();
		},
		routes:{
			"": "home",
			"search": "search"
		},
		home: function(){
			this.options.model.unset("searchPhrase");
			this.options.model.unset("filters.outcode");
			this.options.model.unset("filters.incode");
			this.options.model.unset("filters.city");
			this.options.model.unset("filters.propertyType");
			this.options.model.unset("filters.propertySubType");
			this.options.model.unset("searchResult");
		},
		search: function(params){
			if(params.searchPhrase){
				this.options.model.set("searchPhrase", params.searchPhrase);
			}

			if(params.fieldOrderBy){
				var fieldOrderByClone = this.options.model.get("fieldOrderBy");
				_.findWhere(fieldOrderByClone, {enabled: true}).enabled = false;
				_.findWhere(fieldOrderByClone, {value: params.fieldOrderBy}).enabled = true;
				this.options.model.set("fieldOrderby", fieldOrderByClone);
			}

			if(params.directionOrderBy){
				var directionOrderByClone = this.options.model.get("directionOrderBy");
				_.findWhere(directionOrderByClone, {enabled: true}).enabled = false;
				_.findWhere(directionOrderByClone, {value: params.directionOrderBy}).enabled = true;
				this.options.model.set("directionOrderBy", directionOrderByClone);
			}

			if(params.outcode){
				this.options.model.set("filters.outcode", params.outcode.split(","));
			}
			else{
				this.options.model.unset("filters.outcode");
			}

			if(params.incode){
				this.options.model.set("filters.incode", params.incode.split(","));
			}
			else{
				this.options.model.unset("filters.incode");
			}

			if(params.city){
				this.options.model.set("filters.city", params.city.split(","));
			}
			else{
				this.options.model.unset("filters.city");
			}

			if(params.propertyType){
				this.options.model.set("filters.propertyType", params.propertyType.split(","));
			}
			else{
				this.options.model.unset("filters.propertyType");
			}

			if(params.propertySubType){
				this.options.model.set("filters.propertySubType", params.propertySubType.split(","));
			}
			else{
				this.options.model.unset("filters.propertySubType");
			}

			$.get("/search", this.options.model.getSearchParams(), this.handleSearchResponse);
		},
		handleSearchResponse: function(data){
			this.options.model.set("searchResult", data);
		}
	});
});