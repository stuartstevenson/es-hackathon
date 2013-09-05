define(["jquery", "underscore", "marionette", "handlebars", "text!result/template.html", "result/isTermActiveHelper"], function($, _, Marionette, Handlebars, template, IsFacetActiveHelper){
	return Marionette.ItemView.extend({
		template: Handlebars.compile(template, {noEscape: true}),
		initialize: function(){
			IsFacetActiveHelper.register();
		},
		modelEvents: {
			"change:searchResult": "render"
		},
		events: {
			"change .facet" : "facetChange",
			"click #filterUpdate" : "filterUpdate"
		},
		setRouter: function(router){
			this.router = router;
		},
		facetChange: function(event){
			var $input = $(event.target);
			var isChecked = $input.is(":checked");
			var facetName = $input.data("facet");
			if(!this.model.has("filters." + facetName) && isChecked){
				this.model.set("filters." + facetName, [$input.val()])
			}
			else if(isChecked){
				var newFilterList = this.model.get("filters." + facetName);
				newFilterList.push($input.val());
				this.model.set("filters." + facetName, newFilterList)
			}
			else if(!isChecked){
				var newFilterList = _.without(this.model.get("filters." + facetName), $input.val());
				this.model.set("filters." + facetName, newFilterList);
			}
		},
		filterUpdate: function(){
			this.router.navigate("/search?" + this.model.getSearchParamsForURL(), {
				trigger: true
			});
		}
	});
});