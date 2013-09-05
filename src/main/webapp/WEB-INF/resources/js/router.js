define(["backbone", "underscore"], function (Backbone, _) {
	return Backbone.Router.extend({
		initialize: function(options){
			this.options = options;
			_.bindAll(this, "handleSearchResponse");
			Backbone.history.start();
		},
		routes:{
			"search": "search"
		},
		search: function(params){
			if(params.searchPhrase){
				this.options.model.set("searchPhrase", params.searchPhrase);
			}
			if(params.outcode){
				this.options.model.set("filters.outcode", params.outcode.split(","));
			}
			if(params.incode){
				this.options.model.set("filters.incode", params.incode.split(","));
			}
			if(params.city){
				this.options.model.set("filters.city", params.city.split(","));
			}
			if(params.propertyType){
				this.options.model.set("filters.propertyType", params.propertyType.split(","));
			}
			if(params.propertySubType){
				this.options.model.set("filters.propertySubType", params.propertySubType.split(","));
			}
			$.get("/search", this.options.model.getSearchParams(), this.handleSearchResponse);
		},
		handleSearchResponse: function(data){
			this.options.model.set("searchResult", data);
		}
	});
});