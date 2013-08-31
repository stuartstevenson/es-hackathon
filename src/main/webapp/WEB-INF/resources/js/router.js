define(["backbone", "header/view"], function (Backbone, HeaderView) {
	return Backbone.Router.extend({
		routes:{
			"":"homepage"
		},
		homepage:function () {
			this.application.headerRegion.show(new HeaderView());
		},
		initialize: function(options){
			this.application = options.application;
			Backbone.history.start();
		}
	});
});