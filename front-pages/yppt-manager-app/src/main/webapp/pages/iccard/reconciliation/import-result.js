/**
 * IC卡副卡修改
 */
(function() {
	$(document).ready(function() {
		if(parent.reconciliation){
			$("#resultMessage").html(parent.reconciliation.message);
		}
		if(parent.reconciliationSh){
			$("#resultMessage").html(parent.reconciliationSh.message);
		}
	});
})();