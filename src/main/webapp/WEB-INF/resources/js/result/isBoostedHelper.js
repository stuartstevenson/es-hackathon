define(["handlebars"], function(Handlebars){
	return function(){
		var register = function(){
			Handlebars.registerHelper("isBoosted", function(conditional, options){
				if(this.boost > 0){
					return options.fn(this);
				}
				else{
					return options.inverse(this);
				}
			});
		};
		return {
			register: register
		};
	}();
});