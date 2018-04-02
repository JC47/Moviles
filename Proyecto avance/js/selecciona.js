//document.getElementById('content').innerHTML = '<table id="results"></table>';
var db;
db = openDatabase("DBTest", "1.0", "BD Ejemplo", 200000);
showRecords();
document.getElementById('results').addEventListener('click', function(e){e.preventDefault();}, false);

function showRecords() {
	document.getElementById('results').innerHTML = '';
	db.transaction(function(tx) {
		tx.executeSql("SELECT * FROM ejemplo WHERE id IN (SELECT MAX(id) FROM ejemplo)", [], function(tx, result) {
			for(var i=0, item = null; i < result.rows.length; i++) {
				item = result.rows.item(i);
				document.getElementById('results').innerHTML += item['nombre'];
			}
		});
	});
}