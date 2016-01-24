
/**
*	JODA DATE-TIME UTIL SCRIPT
*/

/**
 * Get Date from Date time object
 * @return String 
 */

var DateUtil = (function () {
	this.getDate = function(data) {
		return data.dayOfMonth+"/"+data.monthOfYear+"/"+data.year;
	};
	return this;
})();
