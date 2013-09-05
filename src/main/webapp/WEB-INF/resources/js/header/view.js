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
					this.model.set("searchPhrase", this.ui.searchBox.val(), {
						silent: true
					});
					this.model.unset("error");
					this.navigate();
				}
				else{
					this.model.unset("searchPhrase", this.ui.searchBox.val(), {
						silet: true
					});
					this.model.set("error", "Woops. Looks like you forgot to enter a search phrase");
				}
			},
			"keypress #searchInput": function(event){
				if(event.which == 13 && this.ui.searchBox.val()){
					this.model.set("searchPhrase", this.ui.searchBox.val(), {
						silent: true
					});
					this.model.unset("error");
					this.navigate();
				}
			}
		},
		modelEvents: {
			"change:error": "render",
			"change:searchPhrase": "render"
		},
		setRouter: function(router){
			this.router = router;
		},
		navigate: function(){
			this.router.navigate("/search?" + this.model.getSearchParamsForURL(), {
				trigger: true
			});
		}
	});
});