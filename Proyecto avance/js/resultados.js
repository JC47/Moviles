//document.getElementById('content').innerHTML = '<table id="results"></table>';
var db;
db = openDatabase("DBTest", "1.0", "BD Ejemplo", 200000);
showRecords();
showBest();
document.getElementById('results').addEventListener('click', function(e){e.preventDefault();}, false);
document.getElementById('resultados').addEventListener('click', function(e){e.preventDefault();}, false);

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

function showBest() {
	document.getElementById('resultados').innerHTML = '<tr><th>Nombre del Jugador</th><th>Tiempo</th><th>Movimientos</th></tr>';
	db.transaction(function(tx) {
		tx.executeSql("SELECT * FROM ejemplo ORDER BY tiempo ASC, movimiento ASC", [], function(tx, result) {
			for(var i=0, item = null; i < result.rows.length; i++) {
				item = result.rows.item(i);
				document.getElementById('resultados').innerHTML += '<tr><td><span contenteditable="false">' + item['nombre'] + '</span></td><td><span contenteditable="false">' + item['tiempo'] + '</span></td><td><span contenteditable="false">' + item['movimiento'] + '</span></td></tr>';
			}
		});
	});
}
