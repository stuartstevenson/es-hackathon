require.config({
    paths: {
		text: "//cdnjs.cloudflare.com/ajax/libs/require-text/2.0.10/text",
        jquery: "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery",
        underscore: "//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.1/underscore",
        backbone: "//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.0.0/backbone",
        marionette: "http://cdnjs.cloudflare.com/ajax/libs/backbone.marionette/1.0.4-bundled/backbone.marionette",
        handlebars: "//cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.rc.2/handlebars",
        bootstrap: "//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0-rc2/js/bootstrap",
		queryparams: "/resources/js/libs/backbone.queryparams"
    },
    shim: {
        jquery: {
            exports: "jQuery"
        },
        bootstrap: {
            deps: [
                "jquery"
            ],
            exports: "jQuery.fn.modal"
        },
        underscore: {
            exports: "_"
        },
        backbone: {
            deps: [
                "jquery",
                "underscore"
            ],
            exports: "Backbone"
        },
		queryparams: {
			deps: [
				"backbone"
			],
			exports: "Backbone.History.prototype.getQueryParameters"
		},
        handlebars: {
            exports: "Handlebars"
        },
        marionette: {
            deps: [
                "backbone"
            ],
            exports: "Backbone.Marionette"
        }
    }
});

define(["app", "bootstrap", "queryparams"], function(application){
    application.start();
});