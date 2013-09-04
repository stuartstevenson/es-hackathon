define(["jquery", "underscore", "marionette", "handlebars", "text!result/template.html", "result/isTermActiveHelper"], function($, _, Marionette, Handlebars, template, IsFacetActiveHelper){
	return Marionette.ItemView.extend({
		template: Handlebars.compile(template, {noEscape: true}),
		initialize: function(){
			_.bindAll(this, "handleSearchResponse");
			IsFacetActiveHelper.register();
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