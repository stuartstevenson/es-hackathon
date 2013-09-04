define(["jquery", "marionette", "handlebars", "text!header/template.html"], function($, Marionette, Handlebars, template){
	return Marionette.ItemView.extend({
		template: Handlebars.compile(template),
		ui: {
			searchBox : "#searchInput",
			searchButton : "#searchButton",
			errorAlert: "#errorAlert"
		},
		events: {
			"click #searchButton": function(){
				if(this.ui.searchBox.val()){
					this.model.unset("error");
					this.router.navigate("search?searchPhrase=" + this.ui.searchBox.val(), {
						trigger: true
					});
				}
				else{
					this.model.set("error", "Woops. Looks like you forgot to enter a search phrase");
				}
			}
		},
		modelEvents: {
			"change:error": "render",
			"change:searchPhrase": "render"
		},
		setRouter: function(router){
			this.router = router;
		}
	});
});