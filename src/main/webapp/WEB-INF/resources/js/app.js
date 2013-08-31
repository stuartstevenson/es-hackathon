define(["marionette", "router"], function (Marionette, Router) {
	var application = new Marionette.Application();
	application.addInitializer(function () {
		this.addRegions({
			headerRegion:"#headerRegion",
			resultRegion:"#resultRegion"
		});
		new Router({
			application:this
		});
	});
	return application;
});