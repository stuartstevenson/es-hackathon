define(["handlebars", "underscore"], function(Handlebars, _){
	return function(){
		var register = function(){
			Handlebars.registerHelper("isTermActive", function(filters, block){
				var filter;
				for(filter in filters){
					if(filter.name === this.parent.name){
						if(_.contains(filter.terms, this.term)){
							return block(this);
						}
					}
				}
			});
		};

		return {
			register: register
		};
	}();
});