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
				this.model.clearFilters();
				this.fireSearchInput();
			},
			"keypress #searchInput": function(event){
				if(event.which == 13 ){
					this.fireSearchInput();
				}
			}
		},
		modelEvents: {
			"change:error": "render",
			"change:searchPhrase": "render",
			"change:searchResult": "render"
		},
		onShow: function(){
			this.focusSearchBoxIfPhraseEmpty();
		},
		onRender: function(){
			this.focusSearchBoxIfPhraseEmpty();
		},
		focusSearchBoxIfPhraseEmpty: function(){
			if(!this.model.has("searchPhrase") || this.model.get("searchPhrase").length == 0){
				this.ui.searchBox.focus();
			}
		},
		setRouter: function(router){
			this.router = router;
		},
		fireSearchInput: function(){
			if(this.ui.searchBox.val()){
				this.model.set("searchPhrase", this.ui.searchBox.val(), {
					silent: true
				});
				this.model.unset("error");
				this.router.navigate("/search?" + this.model.getSearchParamsForURL(), {
					trigger: true
				});
			}
			else{
				this.model.unset("searchPhrase", this.ui.searchBox.val(), {
					silet: true
				});
				this.model.set("error", "Woops. Looks like you forgot to enter a search phrase");
			}
		}
	});
});