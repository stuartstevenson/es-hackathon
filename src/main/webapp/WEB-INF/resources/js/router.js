define(["backbone"], function (Backbone) {
	return Backbone.Router.extend({
		initialize: function(options){
			this.options = options;
			Backbone.history.start();
		},
		routes:{
			"search": "search"
		},
		search: function(params){
			this.options.model.set("searchPhrase", params.searchPhrase);
		}
	});
});