let objects = ['bicycle', 'bicycle', 'leaf', 'leaf', 'cube', 'cube', 'anchor', 'anchor', 'paper-plane-o', 'paper-plane-o', 'bolt', 'bolt', 'bomb', 'bomb', 'diamond', 'diamond','user','user','tree','tree'];
//let objects1 = ['bell','bell','building-o','building-o','flag','flag','heart','heart','compass','compass','image','image','save','save','hourglass-o','hourglass-o','taxi','taxi','plug','plug'];
//let objects2 = ['whatsapp','whatsapp','ambulance','ambulance','android','android','apple','apple','book','book','bug','bug','camera','camera','database','database','youtube','youtube','wordpress','wordpress'];

    // Useful selectors shortened
    var container = document.getElementsByClassName('container');
    var scorePanel = document.getElementsByClassName('score-panel');
    var movesTag = document.getElementById('moves');
    var timer = document.getElementById('timer');
    var restart = document.getElementsByClassName('restart');
    var deck = document.getElementById('deck');

    // Set variables to shorten code
    var nowTime;
    var allOpen = [];
    var match = 0;
    var second = 0;
    var moves = 0;
    var wait = 420;
    var totalCard = objects.length / 2;

// Shuffling function: enables that no two games have the same card arrangement
function shuffle(array) {
    let currentIndex = array.length, temporaryValue, randomIndex;

    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}

// The function init() enables the game to begin
function init() {

    // The shuffle function shuffles the objects array
    let allCards = shuffle(objects);
    deck.innerHTML = '';
    // The game starts with no matching cards and zero moves
    match = 0;
    moves = 0;
    movesTag.innerHTML = 0;

    for (let i = 0; i < allCards.length; i++) {
        deck.innerHTML+='<li class="card"><i class="fa fa-' + allCards[i] + '"></i></li>';
    }
    addCardListener();

    resetTimer(nowTime);
    second = 0;
    timer.innerHTML = ''+second;
    initTime();
}

// Add boostrap modal alert window showing time, moves, score it took to finish the game, toggles when all pairs are matched.
function gameOver(moves) {
  alert("Terminaste en " + second + " segundos, con " +moves+ " movimientos");
  updateRecord(moves, second);
  window.location="puntuacion.html";
  //init();
}

// This function allows each card to be validated that is an equal match to another card that is clicked on to stay open.
// If cards do not match, both cards are flipped back over.
let addCardListener = function () {

    var cardsTemp = deck.getElementsByClassName("card");
    for(let i = 0; i < cardsTemp.length; i++){
      cardsTemp[i].addEventListener('click',function() {
        if(cardsTemp[i].className == 'show' || cardsTemp[i].className == 'match'){return true;}

        let card = cardsTemp[i].innerHTML.toString();
        allOpen.push(card);
        cardsTemp[i].className += ' open show';

        if (allOpen.length > 1) {
            if (card === allOpen[0]) {
                var a = deck.getElementsByClassName('open');
                setTimeout(function() {
                  for(let i=0; i<a.length;i++){
                    a[i].className = "card match";
                  }
                },wait/4);
                setTimeout(function() {
                  for(let i=0; i<a.length;i++){
                    a[i].className = "card match";
                  }
                },wait/4);
                match++;
            } else {
              var x = deck.getElementsByClassName('open');
              setTimeout(function() {
                for(let i=0; i<x.length;i++){
                  x[i].className = "card notmatch";
                }
              },wait);
              setTimeout(function() {
                for(let i=0; i<x.length;i++){
                  x[i].className = "card notmatch";
                }
              },wait);
            }
            allOpen = [];
            moves++;
            movesTag.innerHTML = moves;
        }

        if (totalCard === match) {
            setTimeout(function () {
                gameOver(moves);
            }, 500);
        }

      },false);
    }
}

// Initiates the timer as soon as the game is loaded
function initTime() {
    nowTime = setInterval(function () {
        timer.innerHTML = ''+second;
        second = second + 1
    }, 1000);
}

// Resets the timer when the game ends or is restarted
function resetTimer(timer) {
    if (timer) {
        clearInterval(timer);
    }
}

function restarted() {
  restart.addEventListener('click',function() {
    init();
  },false);
}

init();

//inician funciones de la base de datos
//document.getElementById('content').innerHTML = '<table id="results"></table>';
var db;
db = openDatabase("DBTest", "1.0", "BD Ejemplo", 200000);
showRecords();
document.getElementById('results').addEventListener('click', function(e){e.preventDefault();}, false);

function updateRecord(mov, time) {
	db.transaction(function(tx) {
		tx.executeSql("UPDATE ejemplo SET movimiento = ?, tiempo = ? WHERE id = (SELECT MAX(id) FROM ejemplo)", [mov, time], null, null);
	});
}

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

