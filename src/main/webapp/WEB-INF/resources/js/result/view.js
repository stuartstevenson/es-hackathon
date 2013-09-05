define(["jquery", "underscore", "marionette", "handlebars", "text!result/template.html", "result/isTermActiveHelper", "result/isBoostedHelper"], function($, _, Marionette, Handlebars, template, IsFacetActiveHelper, IsBoostedHelper){
	return Marionette.ItemView.extend({
		template: Handlebars.compile(template, {noEscape: true}),
		initialize: function(){
			IsFacetActiveHelper.register();
			IsBoostedHelper.register();
		},
		modelEvents: {
			"change:searchResult": "render"
		},
		events: {
			"change .facet" : "facetChange",
			"click #filterUpdate" : "filterUpdate",
			"change #fieldOrderBy" : "fieldOrderByUpdate",
			"change #directionOrderBy" : "directionOrderByUpdate"
		},
		ui: {
			fieldOrderBy: "#fieldOrderBy",
			directionOrderBy: "#directionOrderBy"
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
				if(newFilterList.length > 0){
					this.model.set("filters." + facetName, newFilterList);
				}
				else{
					this.model.unset("filters." + facetName);
				}
			}
		},
		filterUpdate: function(){
			this.router.navigate("/search?" + this.model.getSearchParamsForURL(), {
				trigger: true
			});
		},
		fieldOrderByUpdate: function(){
			var $selectedFieldOrderBy = this.ui.fieldOrderBy.find("option:selected");
			var fieldOrderByClone = _.clone(this.model.get("fieldOrderBy"));
			_.findWhere(fieldOrderByClone, {enabled:true}).enabled = false;
			_.findWhere(fieldOrderByClone, {value: $selectedFieldOrderBy.val()}).enabled = true;
			this.model.set("fieldOrderBy", fieldOrderByClone);
			this.filterUpdate();
		},
		directionOrderByUpdate: function(){
			var $selectedDirectionOrderBy = this.ui.directionOrderBy.find("option:selected");
			var directionOrderByClone = _.clone(this.model.get("directionOrderBy"));
			_.findWhere(directionOrderByClone, {enabled:true}).enabled = false;
			_.findWhere(directionOrderByClone, {value: $selectedDirectionOrderBy.val()}).enabled = true;
			this.model.set("directionOrderBy", directionOrderByClone);
			this.filterUpdate();
		}
	});
});