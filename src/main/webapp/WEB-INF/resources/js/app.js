define(["marionette", "router", "model", "header/view", "result/view"], function (Marionette, Router, Model, HeaderView, ResultView) {
	var application = new Marionette.Application();
	application.addInitializer(function () {
		var model = new Model();
		var headerView = new HeaderView({
			model: model
		});
		var resultView = new ResultView({
			model: model
		});
		this.addRegions({
			headerRegion: "#headerRegion",
			resultRegion: "#resultRegion"
		});
		this.headerRegion.show(headerView);
		this.resultRegion.show(resultView);
		var router = new Router({
			model: model
		});
		headerView.setRouter(router);
	});
	return application;
});