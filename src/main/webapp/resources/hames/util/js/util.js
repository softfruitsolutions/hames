/**
 *  Hames Util JS
 */

/**
 * Math Util functions
 */
var MathUtil = (function () {
	var decimalPoints = 2;
	
	//Convert a number to fixed decimal points
	this.toFixed = function(data) {
		return data.toFixed(decimalPoints);
	};
	return this;
})();