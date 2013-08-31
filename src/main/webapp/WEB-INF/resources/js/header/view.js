define(["jquery", "marionette", "handlebars", "header/model", "text!header/template.html"], function($, Marionette, Handlebars, Model, template){
	return Marionette.ItemView.extend({
		model: Model,
		initialize: function(){
			this.model = new Model();
		},
		template: Handlebars.compile(template),
		ui: {
			searchBox : "#search"
		},
		events: {
			"change #search": function(){
				this.model.set("search", this.ui.searchBox.val());
			}
		}
	});
});