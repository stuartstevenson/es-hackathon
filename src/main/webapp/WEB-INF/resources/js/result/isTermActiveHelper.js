define(["handlebars", "underscore"], function(Handlebars, _){
	return function(){
		var register = function(){
			Handlebars.registerHelper("isTermActive", function(filters, name, block){
				if(_.contains(filters[name], this.term)){
					return block.fn(this);
				}
				else{
					return block.inverse(this);
				}
			});
		};

		return {
			register: register
		};
	}();
});