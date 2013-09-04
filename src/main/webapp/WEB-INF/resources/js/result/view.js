define(["jquery", "underscore", "marionette", "handlebars", "text!result/template.html"], function($, _, Marionette, Handlebars, template){
	return Marionette.ItemView.extend({
		template: Handlebars.compile(template),
		initialize: function(){
			_.bindAll(this, "handleSearchResponse");
		},
		modelEvents: {
			"change:searchResult": "render",
			"change:searchPhrase": "search"
		},
		search: function(){
			$.get("/search", {searchPhrase: this.model.get("searchPhrase")}, this.handleSearchResponse);
		},
		handleSearchResponse: function(data){
			this.model.set("searchResult", data);
		}
	});
});